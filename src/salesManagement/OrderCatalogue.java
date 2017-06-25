package salesManagement;

import java.util.ArrayList;
import java.util.Date;

import database.OrderRepository;
import userManagement.UserProfile;

public class OrderCatalogue {
	private ArrayList<Order> orderList;
	
	private static OrderCatalogue orderCatalogue = new OrderCatalogue();
	private OrderRepository repo = new OrderRepository();
	
	public static OrderCatalogue getCatalogue(){
		return OrderCatalogue.orderCatalogue;
	}

	private OrderCatalogue() {
		this.orderList = new ArrayList<>();
	}
	
	public Order getByID(int orderID){
		for (int i=0;i<orderList.size();i++){
			Order currentOrder = orderList.get(i);
			if (currentOrder.getId() == orderID)
				return currentOrder;
		}
		return null;
	}
	
	public void addOrder(Order order){
		this.orderList.add(order);
		this.repo.save(order);
	}
	
	public CustomerOrder createCustomerOrder(Date orderDate, UserProfile orderingUser, ArrayList<ProductElementItem> orderedItems,
			double value, OrderStatus initialStatus, String paymentCode){
		CustomerOrder order = new CustomerOrder(orderDate, orderingUser, orderedItems, value, initialStatus, paymentCode);
		this.addOrder(order);
		return order;
	}
	
	public void intialise(){
		this.orderList = this.repo.getAll();
	}
}
