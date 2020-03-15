package org.insuredoo.batch.config;

import org.insuredoo.model.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class ProductFieldSetMapper implements FieldSetMapper<Product> {

	@Override
    public Product mapFieldSet(FieldSet fieldSet) 
	{
        Product product = new Product();
        product.setCompany(fieldSet.readString("company"));
        product.setProductname(fieldSet.readString("productname"));
        product.setAmount(fieldSet.readDouble("amount"));
        product.setModelfrom(fieldSet.readInt("modelfrom"));
        product.setModelto(fieldSet.readInt("modelto"));
        product.setBrandscovered(fieldSet.readString("brandscovered"));
        product.setTax(fieldSet.readDouble("tax"));
        product.setType(fieldSet.readString("type"));
        return product;

    }
}