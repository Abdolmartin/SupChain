package salesManagement;

class CustomerOrder extends Order{
	private int id;
	private String paymentCode;
	
	public CustomerOrder(){}
	
	public CustomerOrder(String paymentCode){
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
