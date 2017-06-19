package salesManagement;

import java.util.ArrayList;

import common.Constants;
import exceptions.InvalidArgumentException;
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
	
	public void createProductElement(String type, String name, int lowerBound, int higherBound, boolean finality) throws InvalidArgumentException{
		if (this.getByName(name) != null)
			throw new InvalidArgumentException(Constants.INVALID_NAME);
		ProductElement pe = null;
		if (type.equals(Constants.PRODUCT)){
			pe = new Product(name, lowerBound, higherBound, finality);
		}
		else if (type.equals(Constants.COMPONENT)){
			pe = new Component(name, lowerBound, higherBound);
		}
		else{
			throw new IllegalArgumentException(Constants.GHAEDATAN);
		}
		this.addProductElement(pe);
		
	}
	
	public void addProductElement(ProductElement productElement){
		this.productElementList.add(productElement);
	}
	
	public ArrayList<ProductElement> finalProductSearch(String name, double priceLowBound, double priceHighBound){
		return this.generalSearch(Constants.PRODUCT, name, priceLowBound, priceHighBound, true, true);
	}
	
	public ArrayList<ProductElement> generalSearch(String type, String name, double priceLowBound, double priceHighBound, boolean inStock, boolean finality){
		ArrayList<ProductElement> results = new ArrayList<>();
		for (int i=0;i<this.productElementList.size();i++){
			ProductElement productElement = this.productElementList.get(i);
			if (!type.equals(Constants.ANY) && !type.equals(productElement.getType())){
				continue;
			}
			if (!name.equals("") && !name.equals(productElement.getName())){
				continue;
			}
			if (productElement.getLatestPrice() < priceLowBound || productElement.getLatestPrice() > priceHighBound){
				continue;
			}
			if (inStock && productElement.getAvailableQuantity() == 0){
				continue;
			}
			if (finality && (productElement.getType().equals(Constants.COMPONENT) || ((Product)productElement).isFinal())){
				continue;
			}
			
			results.add(productElement);
		}
		return results;
	}
	
	
}
