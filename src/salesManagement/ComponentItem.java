package salesManagement;

class ComponentItem extends ProductElementItem{
	private int id;
	
	public ComponentItem(){}

	public ComponentItem(ProductElement productElementType) {
		super(productElementType);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
