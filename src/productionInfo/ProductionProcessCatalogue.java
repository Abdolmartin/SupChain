package productionInfo;

import java.util.ArrayList;

import common.Constants;
import exceptions.InvalidArgumentException;
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
	
	
}
