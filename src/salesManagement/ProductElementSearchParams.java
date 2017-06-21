package salesManagement;

public class ProductElementSearchParams {
	String type;
	String name;
	double priceLowBound;
	double priceHighBound;
	boolean inStock;
	boolean finality;
	public ProductElementSearchParams(String type, String name, double priceLowBound, double priceHighBound,
			boolean inStock, boolean finality) {
		this.type = type;
		this.name = name;
		this.priceLowBound = priceLowBound;
		this.priceHighBound = priceHighBound;
		this.inStock = inStock;
		this.finality = finality;
	}
	
	
}
