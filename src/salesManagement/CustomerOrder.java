package salesManagement;

import java.util.Date;
import java.util.List;

import userManagement.UserProfile;

class CustomerOrder extends Order{
	private int id;
	private String paymentCode;
	
	public CustomerOrder(){}
		
	public CustomerOrder(Date orderDate, UserProfile orderingUser, List<ProductElementItem> orderedItems,
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
