package salesManagement;

import common.Viewable;

public abstract class ProductElementViewer implements Viewable {
	ProductElement productElement = null;

	public ProductElement getProductElement() {
		return productElement;
	}

	public abstract void setProductElement(ProductElement productElement);	
	
}
