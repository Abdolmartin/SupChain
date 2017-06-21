package ui.handler;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import salesManagement.ProductElementCatalogue;
import salesManagement.ProductElementSearchParams;
import salesManagement.RegularComponentViewer;
import salesManagement.RegularProductViewer;

public class ManagementHandler {
	public ArrayList<JSONObject> productElementSearch(String type, String name, double priceLowBound, double priceHighBound, boolean inStock, boolean finality){
		ProductElementSearchParams searchParams = new ProductElementSearchParams(type, name, priceLowBound, priceHighBound, inStock, finality);
		return ProductElementCatalogue.getCatalogue().showSearchSummary(searchParams);
	}
	
	public JSONObject getProductElementInfo(int productElementID, String type){
		HashMap<String, String> map = new HashMap<>();
		if (type.equals(Constants.COMPONENT)){
			try {
				return ProductElementCatalogue.getCatalogue().viewProductElement(new RegularComponentViewer(), productElementID);
			} catch (InvalidArgumentException e) {
				map.put("error", Constants.GHAEDATAN);
				return new JSONObject(map);
			} catch (NonExistentEntityException e) {
				map.put("error", Constants.NO_SUCH_ENTITY);
				return new JSONObject(map);
			}
		} else if (type.equals(Constants.PRODUCT)){
			try {
				return ProductElementCatalogue.getCatalogue().viewProductElement(new RegularProductViewer(), productElementID);
			} catch (InvalidArgumentException e) {
				map.put("error", Constants.GHAEDATAN);
				return new JSONObject(map);
			} catch (NonExistentEntityException e) {
				map.put("error", Constants.NO_SUCH_ENTITY);
				return new JSONObject(map);
			}
		}
		return null;
	}
	
	public String createProductElement(String type, String name, int lowerBound, int higherBound, String description, boolean finality){
		try {
			ProductElementCatalogue.getCatalogue().createProductElement(type, name, lowerBound, higherBound, description, finality);
			return Constants.SUCCESS;
		} catch (InvalidArgumentException e) {
			return Constants.INVALID_NAME;
		}
	}
	
	public String setInventoryBounds(int productElementID, int lowerBound, int upperBound){
		try {
			ProductElementCatalogue.getCatalogue().setInventoryBounds(productElementID, lowerBound, upperBound);
			return Constants.SUCCESS;
		} catch (InvalidArgumentException e) {
			return Constants.INVALID_INFO;
		} catch (NonExistentEntityException e) {
			return Constants.NO_SUCH_ENTITY;
		}
	}
}
