package salesManagement;

public class UnAvailableItemStatus extends ItemStatus {
	private int id;
	
	public UnAvailableItemStatus(){}

	public UnAvailableItemStatus(double price) {
		super(price);
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

}