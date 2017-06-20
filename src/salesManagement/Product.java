package salesManagement;

import org.json.simple.JSONObject;

import common.Constants;

public class Product extends ProductElement{
	
	boolean finality;

	public Product(String name, int invLowerBound, int invUpperBound, String description, boolean finality) {
		super(name, invLowerBound, invUpperBound, description);
		this.finality = finality;
	}	

	public boolean isFinal() {
		return finality;
	}

	public void setFinality(boolean finality) {
		this.finality = finality;
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

}
