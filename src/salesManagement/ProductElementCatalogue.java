package salesManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import database.ProductElementRepository;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import userManagement.Customer;
public class ProductElementCatalogue {
	
	List<ProductElement> productElementList;
	private ProductElementRepository repo = new ProductElementRepository();
	private static ProductElementCatalogue productElementCatalogue = new ProductElementCatalogue();

	public static ProductElementCatalogue getCatalogue(){
		return ProductElementCatalogue.productElementCatalogue;
	}
	
	private ProductElementCatalogue() {
		this.productElementList = new ArrayList<>();
	}
	
	public ProductElement getByID(int productElementID){
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement currentProductElement = this.productElementList.get(i);
			if (currentProductElement.getId() == productElementID){
				return currentProductElement;
			}
		}
		return null;
	}
	
	public ProductElement getByName(String name){
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement currentProductElement = this.productElementList.get(i);
			if (name.equals(currentProductElement.getName())){
				return currentProductElement;
			}
		}
		return null;
	}
	
	public ProductElement createProductElement(String type, String name, int lowerBound, int higherBound, String description, boolean finality) throws InvalidArgumentException{
		if (this.getByName(name) != null)
			throw new InvalidArgumentException(Constants.INVALID_NAME);
		ProductElement pe = null;
		if (type.equals(Constants.PRODUCT)){
			pe = new Product(name, lowerBound, higherBound, description, finality);
		}
		else if (type.equals(Constants.COMPONENT)){
			pe = new Component(name, lowerBound, higherBound, description);
		}
		else{
			throw new IllegalArgumentException(Constants.GHAEDATAN);
		}
		this.addProductElement(pe);
		return pe;
	}
	
	public void addProductElement(ProductElement productElement){
		this.productElementList.add(productElement);
		System.out.println("&&&&&&&&&&&&&&&& "+this.repo.save(productElement));
	}
	
	public List<ProductElement> search(ProductElementSearchParams searchParams){
		List<ProductElement> results = new ArrayList<>();
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement productElement = this.productElementList.get(i);
			if (!searchParams.type.equals(Constants.ANY) && !searchParams.type.equals(productElement.getType())){
				continue;
			}
			if (!searchParams.name.equals("") && !searchParams.name.equals(productElement.getName())){
				continue;
			}
			if (productElement.getAvailableQuantity() > 0 &&
					(productElement.getLatestPrice() < searchParams.priceLowBound || 
							productElement.getLatestPrice() > searchParams.priceHighBound)){
				continue;
			}
			if (searchParams.inStock && productElement.getAvailableQuantity() == 0){
				continue;
			}
			if (searchParams.finality && (productElement.getType().equals(Constants.COMPONENT) || !((Product)productElement).isFinality())){
				continue;
			}
			results.add(productElement);
		}
		return results;
	}
	
	public JSONObject viewProductElement(ProductElementViewer prElementViewer, int productElementID) throws InvalidArgumentException, NonExistentEntityException{
		ProductElement productElement = this.getByID(productElementID);
		if (productElement == null)
			throw new NonExistentEntityException(Constants.NO_SUCH_ENTITY);
		try{
			prElementViewer.setProductElement(productElement);
		}catch(AssertionError e){
			throw new InvalidArgumentException("Invalid viewer");
		}
		return prElementViewer.showInfo();
	}
	
	public List<JSONObject> showSearchSummary(ProductElementSearchParams searchParams){
		List<ProductElement> searchResults = this.search(searchParams);
		List<JSONObject> result = new ArrayList<>();
		for (int i=0;i<searchResults.size();i++){
			result.add(searchResults.get(i).showSummary());
		}
		return result;
	}
	
	public void setInventoryBounds(int productElementID, int lowerBound, int upperBound) throws InvalidArgumentException, NonExistentEntityException{
		ProductElement productElement = this.getByID(productElementID);
		if (productElement == null)
			throw new NonExistentEntityException(Constants.NO_SUCH_ENTITY);
		productElement.defineInventoryBounds(lowerBound, upperBound);
		this.repo.update(productElement);
	}
	
	public void createReview(int productID, Customer customer, String body, int score, Date date) throws InvalidArgumentException{
		ProductElement pe = this.getByID(productID);
		//Checking that the given ID is the ID of a Product
		if (!pe.getType().equals(Constants.PRODUCT))
			throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
		//TODO Check existence of CustomerOrder
		((Product)pe).createReview(customer, body, score, date);
		this.repo.update(pe);
	}

	public List<ProductElementItem> createItems(int productElementID, int count, ItemStatus initialStatus) throws InvalidArgumentException {
		List<ProductElementItem> result = new ArrayList<>();
		ProductElement pe = this.getByID(productElementID);
		for (int i=0;i<count;i++){
			ProductElementItem item = pe.addItem(initialStatus.clone());
			result.add(item);
		}
		this.repo.update(pe);
		return result;
	}
	
	public List<JSONObject> viewProductElementItems(int productElementID){
		ProductElement pe = this.getByID(productElementID);
		return pe.viewItems();
	}
	
	public List<ProductElementItem> changeItemStati(int productElementID, int[] itemIDs, ItemStatus newStatus){
		ProductElement pe = this.getByID(productElementID);
		List<ProductElementItem> items = pe.changeItemStati(itemIDs, newStatus);
		this.repo.update(pe);
		return items;
	}
	
	public void initialise(){
		this.productElementList = this.repo.getAll();
	}
}
