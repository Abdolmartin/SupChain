package salesManagement;

public class SpentItemStatus extends ItemStatus {

	public SpentItemStatus(double price) {
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
