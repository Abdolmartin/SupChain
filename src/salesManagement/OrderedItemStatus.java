package salesManagement;

import common.Constants;

public class OrderedItemStatus extends ItemStatus {
	private int id;
	
	public OrderedItemStatus(){}

	public OrderedItemStatus(double price) {
		super(price);
	}
	
	@Override
	public String toString(){
		return "ordered";
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
		return new OrderedItemStatus(price);
	}

	@Override
	public String getName() {
		return Constants.ORDERED;
	}

}
