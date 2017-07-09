package ui.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import productionInfo.ProductionProcessCatalogue;
import salesManagement.AvailableItemStatus;
import salesManagement.ItemStatus;
import salesManagement.OrderCatalogue;
import salesManagement.ProductElement;
import salesManagement.ProductElementCatalogue;
import salesManagement.ProductElementSearchParams;
import salesManagement.RegularComponentViewer;
import salesManagement.RegularProductViewer;
import supplyManagement.SupplyPathCatalogue;

public class OrganisationManagementFacade {
	public List<JSONObject> productElementSearch(String type, String name, double priceLowBound, double priceHighBound, boolean inStock, boolean finality){
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
	
	public String getProductElementType(int productElementID){
		ProductElement pe = ProductElementCatalogue.getCatalogue().getByID(productElementID);
		return pe.getType();
	}
	
	public String createProductElement(String type, String name, int lowerBound, int higherBound, String description, boolean finality){
		try {
			ProductElementCatalogue.getCatalogue().createProductElement(type, name, lowerBound, higherBound, description, finality);
			return Constants.SUCCESS;
		} catch (InvalidArgumentException e) {
			return Constants.INVALID_NAME;
		}
	}
	
	public String createProductionProcess(int[] inputIDs, int[] outputIDs, String name, String[] sections){
		try{
			ProductionProcessCatalogue.getCatalogue().createProductionProcess(inputIDs, outputIDs, name, sections);
		}catch(Exception e){
			return Constants.GHAEDATAN;
		}
		return Constants.SUCCESS;
	}
	
	public String createSupplyPath(String supplierName, String name,
			int[] componentIDs, String emailAddress, String telephoneNumber, String physicalAddress){
		try{
			SupplyPathCatalogue.getCatalogue().createSupplyPath(supplierName,
					name, componentIDs, emailAddress, telephoneNumber, physicalAddress);
		}catch(Exception e){
			return Constants.GHAEDATAN;
		}
		return Constants.SUCCESS;
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
	
	public String addProductElementInventory(int productElementID, int count, double price){
		try {
			ProductElementCatalogue.getCatalogue().createItems(productElementID, count, new AvailableItemStatus(price));
		} catch (InvalidArgumentException e) {
			return Constants.GHAEDATAN;
		}
		return Constants.SUCCESS;
	}
	
	//Long live the Roman Empire.
	public String changeItemStati(int productElementID, int[] itemIDs, ItemStatus newStatus){
		try {
			ProductElementCatalogue.getCatalogue().changeItemStati(productElementID, itemIDs, newStatus);
		} catch (Exception e) {
			return Constants.GHAEDATAN;
		}
		return Constants.SUCCESS;
	}
	
	public List<JSONObject> getProductElementItems(int productElementID){
		return ProductElementCatalogue.getCatalogue().viewProductElementItems(productElementID);
	}

	public int createCustomerOrder(int userID, int productID, int count) {
		try{
			int id = OrderCatalogue.getCatalogue().createCustomerOrder(userID, productID, count);
			return id;
		}catch(Exception e){
			return -1;
		}
	}

	public String addOrderPayment(int orderID, String paymentCode) {
		try {
			OrderCatalogue.getCatalogue().addPaymentCode(orderID, paymentCode);
			return Constants.SUCCESS;
		} catch (InvalidArgumentException e) {
			return Constants.GHAEDATAN;
		}
	}

	public void cancelOrder(int orderID) {
		OrderCatalogue.getCatalogue().cancelOrder(orderID);
	}
}
