package org.insuredoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer
{
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 	registry.addResourceHandler("/js/**","/css/**").addResourceLocations("classpath:/static/js/","classpath:/static/css/");
		}
	 
	
}