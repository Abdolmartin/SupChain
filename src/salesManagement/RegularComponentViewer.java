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
		result.put("lower", String.valueOf(component.getInvLowerBound()));
		result.put("upper", String.valueOf(component.getInvUpperBound()));
		result.put("type", component.getType());
		result.put("inventory", String.valueOf(component.getAvailableQuantity()));
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
