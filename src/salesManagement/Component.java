package salesManagement;

import common.Constants;
import exceptions.InvalidArgumentException;

public class Component extends ProductElement{
	private int id;
	
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
		// TODO 
		
	}

	@Override
	public void changeInventory() {
		// TODO 
		
	}
	
	@Override
	public String getType() {
		return Constants.COMPONENT;
	}

	@Override
	public ProductElementItem addItem(ItemStatus initialStatus) throws InvalidArgumentException {
		ProductElementItem productElementItem = new ComponentItem(this);
		productElementItem.updateStatus(initialStatus);
		this.productElementItemList.add(productElementItem);
		return productElementItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
