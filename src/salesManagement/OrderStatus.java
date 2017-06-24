package salesManagement;

import java.util.Date;

class OrderStatus {
	private int id;
	private boolean paid = false;
	private boolean delivered = false;
	private Date startDate;
	private Order order;
	
	public OrderStatus(){}
	
	public OrderStatus(boolean pain, boolean delivered, Date startDate, Order order){
		this.paid = pain;
		this.delivered = delivered;
		this.startDate = startDate;
		this.order = order;
	}
	
	public OrderStatus(OrderStatus orderStatus){
		this.paid = orderStatus.paid;
		this.delivered = orderStatus.delivered;
		this.startDate = orderStatus.startDate;
		this.order = orderStatus.order;
	}
		
	public Date getStartDate(){
		return this.startDate;
	}
	
	public void setStartDate(Date date){
		this.startDate = date;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
