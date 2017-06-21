package salesManagement;

class CustomerOrder extends Order{

	@Override
	public void addItem(ProductElementItem pElementItem) {
		this.getOrderedItems().add(pElementItem);
	}

}
