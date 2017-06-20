package salesManagement;

class ItemStatus implements ItemAvailabilityStateable{

	@Override
	public boolean isAvailable() {
		return false;
	}

}
