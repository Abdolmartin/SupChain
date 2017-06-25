package salesManagement;

public class AvailableItemStatus extends ItemStatus {
	private int id;

	public AvailableItemStatus(){}
	
	public AvailableItemStatus(double price) {
		super(price);
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

}
