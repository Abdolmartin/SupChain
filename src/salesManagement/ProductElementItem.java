package salesManagement;

import java.util.ArrayList;

class ProductElementItem implements ItemAvailabilityStateable {

	private ProductElement productElementType;
	ArrayList<ItemStatus> statusHistory = new ArrayList<>();
	double price;

	public ProductElementItem(ProductElement productElementType, double price) {
		this.productElementType = productElementType;
		this.price = price;
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
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
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void updateStatus(ItemStatus newStatus){
		//TODO Any necessary checks
		this.statusHistory.add(newStatus);
	}
	
	public ItemStatus getCurrentStatus(){
		return this.statusHistory.get(this.statusHistory.size()-1);
	}

}
