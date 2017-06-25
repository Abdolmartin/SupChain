package salesManagement;

import java.util.ArrayList;

abstract class ProductElementItem implements ItemAvailabilityStateable {

	private int id;
	private ProductElement productElementType;
	private ArrayList<ItemStatus> statusHistory = new ArrayList<>();
	
	public ProductElementItem(){}

	public ProductElementItem(ProductElement productElementType) {
		this.productElementType = productElementType;
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	public ProductElement getProductElementType() {
		return productElementType;
	}

	public void setProductElementType(ProductElement productElementType) {
		this.productElementType = productElementType;
	}

	public ArrayList<ItemStatus> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(ArrayList<ItemStatus> statusHistory) {
		this.statusHistory = statusHistory;
	}

	public double getPrice() {
		return this.getCurrentStatus().getPrice();
	}
	
	public void updateStatus(ItemStatus newStatus){
		newStatus.updateStatus(this);
	}
	
	public ItemStatus getCurrentStatus(){
		return this.statusHistory.get(this.statusHistory.size()-1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
