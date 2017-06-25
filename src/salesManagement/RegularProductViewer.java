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
		result.put("final?", String.valueOf(product.isFinality()));
		result.put("lower", String.valueOf(product.getInvLowerBound()));
		result.put("upper", String.valueOf(product.getInvUpperBound()));
		result.put("type", product.getType());
		result.put("inventory", String.valueOf(product.getAvailableQuantity()));
		result.put("id", String.valueOf(product.getId()));
		//TODO rest of product-specific info
		return result;
	}

	@Override
	public void setProductElement(ProductElement productElement) {
		assert productElement instanceof Product;
		this.productElement = productElement;
	}

}
