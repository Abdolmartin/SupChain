package salesManagement;

public abstract class ItemStatus implements ItemAvailabilityStateable {
	private double price;
	
	public ItemStatus(double price) {
		super();
		this.price = price;
	}

	@Override
	public abstract boolean isAvailable();
	public abstract boolean isDelivered();
	
	public abstract void updateStatus(ProductElementItem pei);
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
}
