package salesManagement;

public class UnAvailableItemStatus extends ItemStatus {

	public UnAvailableItemStatus(double price) {
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
