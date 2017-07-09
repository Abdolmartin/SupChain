package salesManagement;

class ProductItem extends ProductElementItem{
	private int id;
	
	public ProductItem(){}

	public ProductItem(ProductElement productElementType) {
		super(productElementType);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
