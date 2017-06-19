package salesManagement;

import java.util.ArrayList;

public class OrderCatalogue {
	private ArrayList<Order> orderList;
	
	private static OrderCatalogue orderCatalogue = new OrderCatalogue();
	
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
	
}
