package salesManagement;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;

import common.Constants;
import database.ProductElementRepository;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import userManagement.Customer;
public class ProductElementCatalogue {
	
	ArrayList<ProductElement> productElementList;
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
		this.repo.save(productElement);
	}
	
	public ArrayList<ProductElement> search(ProductElementSearchParams searchParams){
		ArrayList<ProductElement> results = new ArrayList<>();
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement productElement = this.productElementList.get(i);
			if (!searchParams.type.equals(Constants.ANY) && !searchParams.type.equals(productElement.getType())){
				System.out.println("shit1");
				continue;
			}
			if (!searchParams.name.equals("") && !searchParams.name.equals(productElement.getName())){
				System.out.println("shit2");
				continue;
			}
			if (productElement.getAvailableQuantity() > 0 &&
					(productElement.getLatestPrice() < searchParams.priceLowBound || 
							productElement.getLatestPrice() > searchParams.priceHighBound)){
				System.out.println("shit3");
				continue;
			}
			if (searchParams.inStock && productElement.getAvailableQuantity() == 0){
				System.out.println("shit4");
				continue;
			}
			if (searchParams.finality && (productElement.getType().equals(Constants.COMPONENT) || ((Product)productElement).isFinality())){
				System.out.println("shit5");
				continue;
			}
			System.out.println("YAY");
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
	
	public ArrayList<JSONObject> showSearchSummary(ProductElementSearchParams searchParams){
		ArrayList<ProductElement> searchResults = this.search(searchParams);
		ArrayList<JSONObject> result = new ArrayList<>();
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

	public void createItems(int productElementID, int count, ItemStatus initialStatus) throws InvalidArgumentException {
		ProductElement pe = this.getByID(productElementID);
		for (int i=0;i<count;i++){
			pe.addItem(initialStatus.clone());
		}
		this.repo.update(pe);
	}
	
	public ArrayList<JSONObject> viewProductElementItems(int productElementID){
		ProductElement pe = this.getByID(productElementID);
		return pe.viewItems();
	}
	
	public void changeItemStati(int productElementID, int[] itemIDs, ItemStatus newStatus){
		ProductElement pe = this.getByID(productElementID);
		pe.changeItemStati(itemIDs, newStatus);
		this.repo.update(pe);
	}
	
	public void initialise(){
		this.productElementList = this.repo.getAll();
	}
}
