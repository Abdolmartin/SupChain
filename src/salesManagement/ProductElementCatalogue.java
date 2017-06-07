package salesManagement;

import java.util.ArrayList;
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
}
