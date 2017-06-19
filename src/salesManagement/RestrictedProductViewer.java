package salesManagement;

import org.json.simple.JSONObject;

public class RestrictedProductViewer extends ProductElementViewer {

	@Override
	public JSONObject showInfo() {
		if (this.productElement == null)
			return null;
		JSONObject result = productElement.showInfo();
		return result;
	}

	@Override
	public void setProductElement(ProductElement productElement) {
		assert productElement instanceof Product;
		this.productElement = productElement;
	}

}
