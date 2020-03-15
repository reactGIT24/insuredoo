package org.insuredoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements Comparable<Product>
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long product_id;
	private String company;
	private String productname;
	private double amount;
	private int modelfrom;
	private int modelto;
	private String brandscovered;
	private double tax;
	private String type;
	private double total;
		
	public Product() {}
	
	public Product(String company,String productname, String type, int modelfrom, int modelto,String brandscovered,double amount, double tax){
		this.company = company;
		this.productname = productname;
		this.brandscovered = brandscovered;
		this.modelfrom = modelfrom;
		this.modelto = modelto;
		this.amount = amount;
		this.tax = tax;
		this.type = type;
	}
	
	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getModelfrom() {
		return modelfrom;
	}

	public void setModelfrom(int modelfrom) {
		this.modelfrom = modelfrom;
	}

	public int getModelto() {
		return modelto;
	}

	public void setModelto(int modelto) {
		this.modelto = modelto;
	}

	public String getBrandscovered() {
		return brandscovered;
	}

	public void setBrandscovered(String brandscovered) {
		this.brandscovered = brandscovered;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(Product o) {
		return  new Double(this.getAmount()).compareTo(o.getAmount());
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", company=" + company + ", productname=" + productname
				+ ", amount=" + amount + ", modelfrom=" + modelfrom + ", modelto=" + modelto + ", brandscovered="
				+ brandscovered + ", tax=" + tax + ", type=" + type + "]";
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	
}
