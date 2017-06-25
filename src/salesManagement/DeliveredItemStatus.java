package salesManagement;

public class DeliveredItemStatus extends ItemStatus {
	private int id;
	
	public DeliveredItemStatus(){}

	public DeliveredItemStatus(double price) {
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
		return true;
	}

	@Override
	public void updateStatus(ProductElementItem pei) {
		if(pei instanceof ComponentItem){
			pei.getStatusHistory().add(new AvailableItemStatus(this.getPrice()));
		}else{
			pei.getStatusHistory().add(this);
		}
		
	}

}
