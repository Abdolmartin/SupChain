package salesManagement;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;

public class RestrictedProductViewer extends ProductElementViewer {

	@Override
	public JSONObject showInfo() throws InvalidArgumentException {
		if (this.productElement == null)
			return null;
		try{
			JSONObject result = productElement.showInfo();
			return result;
		}catch(Exception e){
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		}
	}

}
