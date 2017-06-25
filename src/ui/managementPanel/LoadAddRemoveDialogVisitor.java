package ui.managementPanel;

public class LoadAddRemoveDialogVisitor extends AddRemoveDialogVisitor {

	@Override
	public void visitOtherAddProductDialog(OtherAddProductDialog otherAddProductDialog) {
		otherAddProductDialog.setTitle("افزایش موجودی محصول - سایر");
	}

	@Override
	public void visitOtherAddComponentDialog(OtherAddComponentDialog otherAddComponentDialog) {
		otherAddComponentDialog.setTitle("افزایش موجودی مولفه - سایر");
	}

	@Override
	public void visiOtherRemoveProductDialog(OtherRemoveProductDialog otherRemoveProductDialog) {
		
	}

	@Override
	public void visiOtherRemoveComponentDialog(OtherRemoveComponentDialog otherRemoveComponentDialog) {
		
	}

}
