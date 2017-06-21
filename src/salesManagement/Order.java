package salesManagement;

import java.util.ArrayList;
import java.util.Date;

import userManagement.UserProfile;

public abstract class Order {
	private Date orderDate;
	private UserProfile orderingUser;
	private ArrayList<ProductElementItem> orderedItems;
	private double value;
	private ArrayList<OrderStatus> statusHistory;
	private int id;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public UserProfile getOrderingUser() {
		return orderingUser;
	}

	public void setOrderingUser(UserProfile orderingUser) {
		this.orderingUser = orderingUser;
	}

	public ArrayList<ProductElementItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(ArrayList<ProductElementItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public ArrayList<OrderStatus> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(ArrayList<OrderStatus> statusHistory) {
		this.statusHistory = statusHistory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void updateStatus(OrderStatus newStatus){
		//TODO Any necessary checks
		this.statusHistory.add(newStatus);
	}
	
	public void addItem(ProductElementItem pElementItem){
		this.orderedItems.add(pElementItem);
	};

}
