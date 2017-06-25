package ui.managementPanel;

public class OtherRemoveProductDialog extends AbstractAddOrRemoveDialog {

	public OtherRemoveProductDialog(int userID, int productElementID) {
		super(userID, productElementID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(AddRemoveDialogVisitor v) {
		v.visiOtherRemoveProductDialog(this);
	}

}
