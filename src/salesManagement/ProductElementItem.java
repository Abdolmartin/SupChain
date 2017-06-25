package salesManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import common.Constants;
import common.Viewable;

public abstract class ProductElementItem implements ItemAvailabilityStateable, Viewable {

	private int id;
	private ProductElement productElementType;
	private ArrayList<ItemStatus> statusHistory = new ArrayList<>();
	
	@Override
	public JSONObject showInfo(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.ID, String.valueOf(this.id));
		map.put("typeID", String.valueOf(productElementType.getId()));
		map.put("type", productElementType.getName());
		map.put("status", this.getCurrentStatus().getName());
		return new JSONObject(map);
	}
	
	public ProductElementItem(){}

	public ProductElementItem(ProductElement productElementType) {
		this.productElementType = productElementType;
	}

	@Override
	public boolean isAvailable() {
		return this.getCurrentStatus().isAvailable();
	}

	public ProductElement getProductElementType() {
		return productElementType;
	}

	public void setProductElementType(ProductElement productElementType) {
		this.productElementType = productElementType;
	}

	public ArrayList<ItemStatus> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(ArrayList<ItemStatus> statusHistory) {
		this.statusHistory = statusHistory;
	}

	public double getPrice() {
		return this.getCurrentStatus().getPrice();
	}
	
	public void updateStatus(ItemStatus newStatus){
		newStatus.updateStatus(this);
	}
	
	public ItemStatus getCurrentStatus(){
		return this.statusHistory.get(this.statusHistory.size()-1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
