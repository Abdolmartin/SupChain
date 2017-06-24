package salesManagement;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import common.Summarizable;
import common.Viewable;
import userManagement.Customer;

public class ProductReview implements Summarizable, Viewable{
	Customer customer;
	Product product;
	String body;
	int score;
	Date date;
	
	public ProductReview(Customer customer, Product product, String body, int score, Date date) {
		super();
		this.customer = customer;
		this.product = product;
		this.body = body;
		this.score = score;
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("customer", this.customer.getUsername());
		map.put("score", String.valueOf(this.score));
		map.put("body", this.body);
		map.put("date", this.date.toString());
		map.put("product", this.product.getName());
		return new JSONObject(map);
	}

	@Override
	public JSONObject showSummary() {
		HashMap<String, String> map = new HashMap<>();
		map.put("customer", this.customer.getUsername());
		map.put("score", String.valueOf(this.score));
		map.put("date", this.date.toString());
		return new JSONObject(map);
	}
	
}
