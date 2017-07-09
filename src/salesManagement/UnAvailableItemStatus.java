package salesManagement;

import common.Constants;

public class UnAvailableItemStatus extends ItemStatus {
	private int id;
	
	public UnAvailableItemStatus(){}

	public UnAvailableItemStatus(double price) {
		super(price);
	}
	
	@Override
	public String toString(){
		return "unavailable";
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	@Override
	public boolean isAvailable() {
		return false;
	}

	@Override
	public boolean isDelivered() {
		return false;
	}

	@Override
	public void updateStatus(ProductElementItem pei) {
		pei.getStatusHistory().add(this);
	}

	@Override
	public ItemStatus clone() {
		return new UnAvailableItemStatus(id);
	}

	@Override
	public String getName() {
		return Constants.UNAVAIL;
	}

}
