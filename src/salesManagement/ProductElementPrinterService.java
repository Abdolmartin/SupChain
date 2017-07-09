package salesManagement;

import java.util.ArrayList;
import java.util.List;

public class ProductElementPrinterService {
	public List<String> getNamesList(List<ProductElement> productElements){
		ArrayList<String> results = new ArrayList<>();
		for (int i=0;i<productElements.size();i++){
			results.add(productElements.get(i).getName());
		}
		return results;
	}
}
