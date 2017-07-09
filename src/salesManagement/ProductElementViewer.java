package salesManagement;

import common.Viewable;

public abstract class ProductElementViewer implements Viewable {
	Viewable productElement = null;

	public void setProductElement(ProductElement productElement) {
		this.productElement = productElement;
	}
}
