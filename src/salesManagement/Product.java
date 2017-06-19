package salesManagement;

import org.json.simple.JSONObject;

public class Product extends ProductElement{
	
	boolean finality;

	public Product(String name, int invLowerBound, int invUpperBound, boolean finality) {
		super(name, invLowerBound, invUpperBound);
		this.finality = finality;
	}

	@Override
	public JSONObject showInfo() {
		// TODO Auto-generated method stub
		return null;
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

}
