package salesManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.Summarizable;
import common.Viewable;
import userManagement.UserProfile;

public abstract class Order implements Viewable, Summarizable{
	private Date orderDate;
	private UserProfile orderingUser;
	private List<ProductElementItem> orderedItems;
	private double value;
	private List<OrderStatus> statusHistory;
	private int id;
	
	@Override
	public JSONObject showSummary(){
		HashMap<String, String> map = new HashMap<>();
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("date", this.getOrderDate().toString());
		map.put("user", this.getOrderingUser().getUsername());
		map.put("value", String.valueOf(this.getValue()));
		map.put("status", this.getCurrentStatus().toString());
		map.put("type", this.getType());
		map.put("element type", this.getOrderedItems().get(0).getTypeName());
		map.put("count", String.valueOf(this.getOrderedItems().size()));
		return new JSONObject(map);
	}
	
	@Override
	public JSONObject showInfo(){
		HashMap<String, String> map = new HashMap<>();
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("date", this.getOrderDate().toString());
		map.put("user", this.getOrderingUser().getUsername());
		map.put("value", String.valueOf(this.getValue()));
		map.put("status", this.getCurrentStatus().toString());
		map.put("type", this.getOrderedItems().get(0).getTypeName());
		map.put("count", String.valueOf(this.getOrderedItems().size()));
		return new JSONObject(map);
	}
	
	public Order(){}
	
	public Order(Date orderDate, UserProfile orderingUser,
			List<ProductElementItem> orderedItems, double value, OrderStatus initialStatus) {
		super();
		this.orderDate = orderDate;
		this.orderingUser = orderingUser;
		this.orderedItems = orderedItems;
		this.value = value;
		this.statusHistory = new ArrayList<>();
		this.updateStatus(initialStatus);
	}

	public Date getOrderDate() {
		return orderDate;
	}
	
	public abstract String getType();

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public UserProfile getOrderingUser() {
		return orderingUser;
	}

	public void setOrderingUser(UserProfile orderingUser) {
		this.orderingUser = orderingUser;
	}

	public List<ProductElementItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<ProductElementItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<OrderStatus> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(List<OrderStatus> statusHistory) {
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
	}
	/*
	public void paid(){
		OrderStatus orderStat = this.statusHistory.get(statusHistory.size()-1);
		OrderStatus newOrderStat = new OrderStatus(orderStat);
		newOrderStat.setPaid(true);
		this.statusHistory.add(newOrderStat);
	}
	
	public void delivered(){
		OrderStatus orderStat = this.statusHistory.get(statusHistory.size()-1);
		OrderStatus newOrderStat = new OrderStatus(orderStat);
		newOrderStat.setDelivered(true);
		this.statusHistory.add(newOrderStat);
	}
	*/
	public OrderStatus getCurrentStatus(){
		return this.getStatusHistory().get(this.getStatusHistory().size()-1);
	}

}
