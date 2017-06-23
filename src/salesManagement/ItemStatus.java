package salesManagement;

public class ItemStatus implements ItemAvailabilityStateable{
	
	private double price;
	
	
	public ItemStatus(double price) {
		super();
		this.price = price;
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

}
