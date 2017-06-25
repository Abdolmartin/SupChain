package salesManagement;

public abstract class ItemStatus implements ItemAvailabilityStateable {
	private int id;
	private double price;
	
	public ItemStatus(){}
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
