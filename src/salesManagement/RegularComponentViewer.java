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
		result.put("lower", component.getInvLowerBound());
		result.put("upper", component.getInvUpperBound());
		result.put("type", component.getType());
		result.put("inventory", component.getAvailableQuantity());
		result.put("id", String.valueOf(component.getId()));
		//TODO rest of component-specific info
		return result;
	}

	@Override
	public void setProductElement(ProductElement productElement) {
		assert productElement instanceof Component;
		this.productElement = productElement;
	}

}
