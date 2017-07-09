package productionInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import salesManagement.ProductElement;
import salesManagement.ProductElementCatalogue;
import userManagement.UserProfile;

public class ProductionProcessCatalogue {
	
	private ArrayList<ProductionProcess> productionProcessList;
	
	private static ProductionProcessCatalogue productionProcessCatalogue = new ProductionProcessCatalogue();
	
	public static ProductionProcessCatalogue getCatalogue(){
		return ProductionProcessCatalogue.productionProcessCatalogue;
	}
	
	private ProductionProcessCatalogue(){
		productionProcessList = new ArrayList<>();
	}
	
	public void addProductionProcess(ProductionProcess p){
		this.productionProcessList.add(p);
	}
	
	public ProductionProcess getByID(int id) throws InvalidArgumentException{
		for (int i=0;i<this.productionProcessList.size();i++){
			ProductionProcess currentProductionProcess = this.productionProcessList.get(i);
			if (currentProductionProcess.getId() == id){
				return currentProductionProcess;
			}
		}
		throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
	}
	
	public void createProductionProcess(int[] inputIDs, int[] outputIDs, String name, String[] sections){
		ArrayList<ProductElement> inputs = new ArrayList<>(), outputs = new ArrayList<>();
		
		for (int i=0;i<inputIDs.length;i++){
			inputs.add(ProductElementCatalogue.getCatalogue().getByID(inputIDs[i]));
		}
		for (int i=0;i<outputIDs.length;i++){
			outputs.add(ProductElementCatalogue.getCatalogue().getByID(outputIDs[i]));
		}
		
		ProductionProcess productionProcess = new ProductionProcess(inputs, outputs, name, 
				new ArrayList<String>(Arrays.asList(sections)));
		
		this.addProductionProcess(productionProcess);
	}
	
	public ArrayList<ProductionProcess> search(){
		return this.productionProcessList;
	}
	
	public List<JSONObject> showSearchSummary(){
		List<ProductionProcess> searchResult = this.search();
		List<JSONObject> results = new ArrayList<>();
		
		for (int i=0;i<searchResult.size();i++){
			results.add(searchResult.get(i).showSummary());
		}
		return results;
	}
	
	public JSONObject getProductionProcessInfo(int id) throws InvalidArgumentException{
		ProductionProcess proc = this.getByID(id);
		return proc.showInfo();
	}
}
