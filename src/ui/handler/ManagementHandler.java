package ui.handler;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import salesManagement.ProductElementCatalogue;
import salesManagement.ProductElementSearchParams;

public class ManagementHandler {
	public ArrayList<JSONObject> productElementSearch(String type, String name, double priceLowBound, double priceHighBound, boolean inStock, boolean finality){
		ProductElementSearchParams searchParams = new ProductElementSearchParams(type, name, priceLowBound, priceHighBound, inStock, finality);
		return ProductElementCatalogue.getCatalogue().showSearchSummary(searchParams);
	}
	
}
