package salesManagement;

import java.util.Date;

abstract class OrderStatus {
	private int id;
	private boolean paid = false;
	private boolean delivered = false;
	private Date startDate;
	private Order order;
		
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
