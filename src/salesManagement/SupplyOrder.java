package salesManagement;

class SupplyOrder extends Order{

	@Override
	public void addItem(ProductElementItem pElementItem) {
		this.getOrderedItems().add(pElementItem);
	}

}
