package ui.managementPanel;

public abstract class AddRemoveDialogVisitor {

	public abstract void visitOtherAddProductDialog(OtherAddProductDialog otherAddProductDialog);

	public abstract void visitOtherAddComponentDialog(OtherAddComponentDialog otherAddComponentDialog);

	public abstract void visiOtherRemoveProductDialog(OtherRemoveProductDialog otherRemoveProductDialog);

	public abstract void visiOtherRemoveComponentDialog(OtherRemoveComponentDialog otherRemoveComponentDialog);
	
}
