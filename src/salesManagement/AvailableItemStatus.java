package salesManagement;

public class AvailableItemStatus extends ItemStatus {

	public AvailableItemStatus(double price) {
		super(price);
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
