package salesManagement;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
public class ProductElementCatalogue {
	
	ArrayList<ProductElement> productElementList;
	
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
	
	public void createProductElement(String type, String name, int lowerBound, int higherBound, String description, boolean finality) throws InvalidArgumentException{
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
		
	}
	
	public void addProductElement(ProductElement productElement){
		this.productElementList.add(productElement);
	}
	
	public ArrayList<ProductElement> search(ProductElementSearchParams searchParams){
		ArrayList<ProductElement> results = new ArrayList<>();
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement productElement = this.productElementList.get(i);
			if (!searchParams.type.equals(Constants.ANY) && !searchParams.type.equals(productElement.getType())){
				continue;
			}
			if (!searchParams.name.equals("") && !searchParams.name.equals(productElement.getName())){
				continue;
			}
			if (productElement.getLatestPrice() < searchParams.priceLowBound || productElement.getLatestPrice() > searchParams.priceHighBound){
				continue;
			}
			if (searchParams.inStock && productElement.getAvailableQuantity() == 0){
				continue;
			}
			if (searchParams.finality && (productElement.getType().equals(Constants.COMPONENT) || ((Product)productElement).isFinal())){
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
	}
}
