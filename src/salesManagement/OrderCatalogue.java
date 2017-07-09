package salesManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import database.OrderRepository;
import exceptions.InvalidArgumentException;
import supplyManagement.SupplyPath;
import supplyManagement.SupplyPathCatalogue;
import userManagement.UserProfile;
import userManagement.UserProfileCatalogue;

public class OrderCatalogue {
	private List<Order> orderList;
	
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
	
	public int createSupplyOrder(int userID, int componentID, int pathID, int count, double price) throws InvalidArgumentException{
		UserProfile orderingUser = UserProfileCatalogue.getCatalogue().getByID(userID);
		Component component = (Component) ProductElementCatalogue.getCatalogue().getByID(componentID);
		ItemStatus initialItemStatus = new OrderedItemStatus(price);
		List<ProductElementItem> items = ProductElementCatalogue.getCatalogue().
				createItems(componentID, count, initialItemStatus);
		double value = price*count;
		SupplyPath path = SupplyPathCatalogue.getCatalogue().getByID(pathID);
		OrderStatus initialStatus = new UndeliveredOrderStatus();
		Order order = this.addSupplyOrder(new Date(), orderingUser, items, value, initialStatus, path);
		return order.getId();
	}
	
	public int createCustomerOrder(int userID, int productID, int count) throws InvalidArgumentException{
		UserProfile orderingUser = UserProfileCatalogue.getCatalogue().getByID(userID);
		Product product = (Product) ProductElementCatalogue.getCatalogue().getByID(productID);
		List<ProductElementItem> orderedItems = product.getAvailableItems(count, new UnAvailableItemStatus(product.getLatestPrice()));
		System.out.println(product.getLatestPrice());
		double value = count*product.getLatestPrice();
		OrderStatus initialStatus = new UnpaidOrderStatus();
		Order order = this.addCustomerOrder(new Date(), orderingUser, orderedItems, value, initialStatus, null);
		return order.getId();
	}
	
	public void addPaymentCode(int orderID, String paymentCode) throws InvalidArgumentException{
		Order order = this.getByID(orderID);
		if (order == null)
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		if (order instanceof SupplyOrder)
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		((CustomerOrder)order).setPaymentCode(paymentCode);
		this.repo.update(order);
	}
	
	public CustomerOrder addCustomerOrder(Date orderDate, UserProfile orderingUser, List<ProductElementItem> orderedItems,
			double value, OrderStatus initialStatus, String paymentCode){
		CustomerOrder order = new CustomerOrder(orderDate, orderingUser, orderedItems, value, initialStatus, paymentCode);
		this.addOrder(order);
		this.repo.save(order);
		return order;
	}
	
	public SupplyOrder addSupplyOrder(Date orderDate, UserProfile orderingUser, List<ProductElementItem> orderedItems,
			double value, OrderStatus initialStatus, SupplyPath path){
		SupplyOrder order = new SupplyOrder(orderDate, orderingUser, orderedItems, value, initialStatus, path);
		this.addOrder(order);
		this.repo.save(order);
		return order;
	}
	
	public void intialise(){
		this.orderList = this.repo.getAll();
	}

	public void cancelOrder(int orderID) {
		Order order = this.getByID(orderID);
		if (order!=null){
			order.updateStatus(new CancelledOrderStatus());
		}
	}
	
	public List<JSONObject> viewAllOrders(){
		List<JSONObject> result = new ArrayList<>();
		for (int i=0;i<this.orderList.size();i++){
			result.add(this.orderList.get(i).showSummary());
		}
		return result;
	}
}
