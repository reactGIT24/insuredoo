package org.insuredoo.batch.config;

import java.util.HashMap;
import java.util.Map;

import org.insuredoo.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor implements ItemProcessor <Product, Product>{

	private static final Map<String,String> PRODUCTS_TYPE = new HashMap<String, String>();
	
	
	public ProductProcessor() {
		PRODUCTS_TYPE.put("is", "Islamic");
		PRODUCTS_TYPE.put("reg", "Non Islamic");
	}
	
	@Override
	public Product process(Product item) throws Exception {
		String typeCode = item.getType();
		String type = PRODUCTS_TYPE.get(typeCode);
		item.setType(type);
		return item;
	}

}
