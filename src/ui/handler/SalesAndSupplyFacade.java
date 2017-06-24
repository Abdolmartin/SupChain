package ui.handler;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import salesManagement.ProductElementCatalogue;
import salesManagement.ProductElementSearchParams;
import salesManagement.RestrictedProductViewer;

public class SalesAndSupplyFacade {
	public ArrayList<JSONObject> finalProductSearch(String name, double priceLowBound, double priceHighBound){
		ProductElementSearchParams searchParams = new ProductElementSearchParams(Constants.PRODUCT, name, priceLowBound, priceHighBound, true, true);
		return ProductElementCatalogue.getCatalogue().showSearchSummary(searchParams);
	}
	
	public JSONObject getProductElementInfo(int productElementID, String type){
		if (type.equals(Constants.PRODUCT)){
			try {
				return ProductElementCatalogue.getCatalogue().viewProductElement(new RestrictedProductViewer(), productElementID);
			} catch (InvalidArgumentException e) {
				HashMap<String, String> map = new HashMap<>();
				map.put("error", Constants.GHAEDATAN);
				return new JSONObject(map);
			} catch (NonExistentEntityException e) {
				HashMap<String, String> map = new HashMap<>();
				map.put("error", Constants.NO_SUCH_ENTITY);
				return new JSONObject(map);
			}
		}
		return null;
	}
	
}
