package salesManagement;

import org.json.simple.JSONObject;

public class Component extends ProductElement{

	public Component(String name, int invLowerBound, int invUpperBound) {
		super(name, invLowerBound, invUpperBound);
		
	}

	@Override
	public JSONObject showInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkItemValidity(ProductElementItem pElementItem) {
		if (pElementItem instanceof ComponentItem)
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
