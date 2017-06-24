package salesManagement;

public class DeliveredItemStatus extends ItemStatus {

	public DeliveredItemStatus(double price) {
		super(price);
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
