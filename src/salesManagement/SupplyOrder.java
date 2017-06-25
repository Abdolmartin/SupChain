package salesManagement;

import java.util.Date;
import java.util.List;

import supplyManagement.SupplyPath;
import userManagement.UserProfile;

class SupplyOrder extends Order{
	private int id;
	private SupplyPath path;
	
	public SupplyOrder(){}
	
	public SupplyOrder(Date orderDate, UserProfile orderingUser, List<ProductElementItem> orderedItems,
			double value, OrderStatus initialStatus, SupplyPath path) {
		super(orderDate, orderingUser, orderedItems, value, initialStatus);
		this.path = path;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public SupplyPath getPath() {
		return path;
	}

	public void setPath(SupplyPath path) {
		this.path = path;
	}

}
