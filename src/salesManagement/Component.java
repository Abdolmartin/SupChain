package salesManagement;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;

public class Component extends ProductElement{
	
	public Component(){}
	
	public Component(String name, int invLowerBound, int invUpperBound, String description) {
		super(name, invLowerBound, invUpperBound, description);
		
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
	
	@Override
	public String getType() {
		return Constants.COMPONENT;
	}

	@Override
	public void addItem(ItemStatus initialStatus) throws InvalidArgumentException {
		ProductElementItem productElementItem = new ComponentItem(this);
		productElementItem.updateStatus(initialStatus);
		this.productElementItemList.add(productElementItem);
	}
	
}
