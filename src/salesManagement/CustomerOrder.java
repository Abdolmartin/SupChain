package salesManagement;

import java.util.ArrayList;
import java.util.Date;

import userManagement.UserProfile;

class CustomerOrder extends Order{
	private int id;
	private String paymentCode;
	
	public CustomerOrder(){}
		
	public CustomerOrder(Date orderDate, UserProfile orderingUser, ArrayList<ProductElementItem> orderedItems,
			double value, OrderStatus initialStatus, String paymentCode) {
		super(orderDate, orderingUser, orderedItems, value, initialStatus);
		this.paymentCode = paymentCode;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getPaymentCode() {
		return paymentCode;
	}
	
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

}
