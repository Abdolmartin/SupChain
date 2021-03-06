package salesManagement;

import common.Constants;

public class AvailableItemStatus extends ItemStatus {
	private int id;

	public AvailableItemStatus(){}
	
	public AvailableItemStatus(double price) {
		super(price);
	}
	
	@Override
	public String toString(){
		return "available";
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	@Override
	public boolean isAvailable() {
		return true;
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
		return new AvailableItemStatus(this.getPrice());
	}

	@Override
	public String getName() {
		return Constants.AVAIL;
	}

}
