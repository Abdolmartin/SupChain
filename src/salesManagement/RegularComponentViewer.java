package salesManagement;

import org.json.simple.JSONObject;

public class RegularComponentViewer extends ProductElementViewer {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject showInfo() {
		if (this.productElement == null)
			return null;
		JSONObject result = productElement.showInfo();
		Component component = (Component) productElement;
		result.put("lower bound", component.getInvLowerBound());
		result.put("upper bound", component.getInvUpperBound());
		result.put("available quantity", component.getAvailableQuantity());
		//TODO rest of component-specific info
		return result;
	}

	@Override
	public void setProductElement(ProductElement productElement) {
		assert productElement instanceof Component;
		this.productElement = productElement;
	}

}
