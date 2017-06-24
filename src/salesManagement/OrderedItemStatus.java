package salesManagement;

public class OrderedItemStatus extends ItemStatus {

	public OrderedItemStatus(double price) {
		super(price);
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
