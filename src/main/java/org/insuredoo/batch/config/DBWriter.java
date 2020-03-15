package org.insuredoo.batch.config;

import java.util.List;

import org.insuredoo.model.Product;
import org.insuredoo.repository.ProductRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<Product>{

	@Autowired ProductRepository productRepository;
	@Override
	public void write(List<? extends Product> products) throws Exception {
	    System.out.println("Data Save for Product ");
		productRepository.saveAll(products);
	}

	

}
