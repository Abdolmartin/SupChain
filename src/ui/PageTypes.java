package ui;

public enum PageTypes {	
	FinalProductSearch("جستجوی محصولات"),
	ProductElementSearch("جستجوی محصولات و مولفه‌ها"),
	CreateProductElement("ایجاد محصول/مولفه‌ی جدید"),
	CreateUser("ایجاد کاربر جدید"),
	CreateProductionProcess("ایجاد فرآیند تولید جدید"),
	ViewOrdersList("مشاهده‌ی فهرست سفارش‌ها"),
	CreateSupplyPath("ایجاد مسیر تامین جدید");
	
	String caption;

	private PageTypes(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}
	
	
	
}
