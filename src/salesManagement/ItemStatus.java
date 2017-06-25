package salesManagement;

public abstract class ItemStatus implements ItemAvailabilityStateable {
	protected int id;
	protected double price;
	
	public ItemStatus(){}
	
	public ItemStatus(double price) {
		super();
		this.price = price;
	}
	
	public abstract ItemStatus clone();

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
