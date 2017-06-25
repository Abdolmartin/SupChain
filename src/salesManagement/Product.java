package salesManagement;

import java.util.Date;
import java.util.List;

import common.Constants;
import exceptions.InvalidArgumentException;
import userManagement.Customer;

public class Product extends ProductElement{
	private int id;
	boolean finality;
	List<ProductReview> reviews;
	
	public Product(){}

	public Product(String name, int invLowerBound, int invUpperBound, String description, boolean finality) {
		super(name, invLowerBound, invUpperBound, description);
		this.finality = finality;
	}	

	public boolean isFinality() {
		return finality;
	}

	public void setFinality(boolean finality) {
		this.finality = finality;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean checkItemValidity(ProductElementItem pElementItem) {
		if (pElementItem instanceof ProductItem)
			return true;
		return false;
	}

	@Override
	public void handleLowerThanBound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return Constants.PRODUCT;
	}
	
	@Override
	public void addItem(ItemStatus initialStatus) throws InvalidArgumentException {
		ProductElementItem productElementItem = new ProductItem(this);
		productElementItem.updateStatus(initialStatus);
		this.productElementItemList.add(productElementItem);
	}
	
	public void addReview(ProductReview review){
		this.reviews.add(review);
	}
	
	public void createReview(Customer customer, String body, int score, Date date){
		ProductReview review = new ProductReview(customer, this, body, score, date);
		this.addReview(review);
	}

	public List<ProductReview> getReviews() {
		return  reviews;
	}

	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}

}
