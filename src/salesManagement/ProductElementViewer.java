package salesManagement;

import common.Viewable;

public abstract class ProductElementViewer implements Viewable {
	Viewable productElement = null;

	public abstract void setProductElement(ProductElement productElement);
}
