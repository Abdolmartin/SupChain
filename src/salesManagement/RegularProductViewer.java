package salesManagement;

import org.json.simple.JSONObject;

public class RegularProductViewer extends ProductElementViewer {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject showInfo() {
		if (this.productElement == null)
			return null;
		JSONObject result = productElement.showInfo();
		Product product = (Product) productElement;
		result.put("final?", String.valueOf(product.isFinal()));
		result.put("lower bound", product.getInvLowerBound());
		result.put("upper bound", product.getInvUpperBound());
		result.put("available quantity", product.getAvailableQuantity());
		//TODO rest of product-specific info
		return result;
	}

	@Override
	public void setProductElement(ProductElement productElement) {
		assert productElement instanceof Product;
		this.productElement = productElement;
	}

}
