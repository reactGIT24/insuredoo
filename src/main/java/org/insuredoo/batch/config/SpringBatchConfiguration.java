package org.insuredoo.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

import org.insuredoo.model.Product;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration
{
	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
        
    
	@Bean
	public Job importProductsJob() {
		
		Step step1 = stepBuilderFactory.get("File-Load")
				.<Product,Product>chunk(100)
				.reader(reader())
				.processor(processor())
				.writer(dbWriter())
				.build();
		
		return (Job) jobBuilderFactory
		       .get("PRODUCTS-LOAD")
		       .incrementer(new RunIdIncrementer())
		       .start(step1)
		       .build();
	}
	
	
	@Bean
    public FlatFileItemReader<Product> reader() {
    	String p = System.getProperty("user.dir");
    	return new FlatFileItemReaderBuilder<Product>()
                .name("CVS-Reader")
                .resource(new FileSystemResource(p+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"motorproducts.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"company","productname","type","modelfrom","modelto","brandscovered","amount","tax"})
                .lineMapper(lineMapper())
                .build();
    	   	
    }
	
	
	 @Bean
	 public LineMapper<Product> lineMapper() {
	        final DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
	        defaultLineMapper.setLineTokenizer(lineTokenizer());
	        defaultLineMapper.setFieldSetMapper(new ProductFieldSetMapper());
	        return defaultLineMapper;
	    }
	 
	@Bean
	 public ProductProcessor processor(){
	        return new ProductProcessor();
	 }
	
	 
	 @Bean
	 public DBWriter dbWriter(){
		 return new DBWriter();
	 }
	 
	 @Bean
	    public LineTokenizer lineTokenizer() {
	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	        lineTokenizer.setDelimiter(",");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames(new String[] {"company","productname","type","modelfrom","modelto","brandscovered","amount","tax"});
	        return lineTokenizer;
	        
	    }

}
