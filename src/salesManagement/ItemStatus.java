package salesManagement;

class ItemStatus implements ItemAvailabilityStateable{
	
	private double price;
	

	@Override
	public boolean isAvailable() {
		return false;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

}
