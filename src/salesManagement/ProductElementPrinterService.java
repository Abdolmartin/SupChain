package salesManagement;

import java.util.ArrayList;

public class ProductElementPrinterService {
	public ArrayList<String> getNamesList(ArrayList<ProductElement> productElements){
		ArrayList<String> results = new ArrayList<>();
		for (int i=0;i<productElements.size();i++){
			results.add(productElements.get(i).getName());
		}
		return results;
	}
}
