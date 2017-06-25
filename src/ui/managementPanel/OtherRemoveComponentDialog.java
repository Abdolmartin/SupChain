package ui.managementPanel;

public class OtherRemoveComponentDialog extends AbstractAddOrRemoveDialog {

	public OtherRemoveComponentDialog(int userID, int productElementID) {
		super(userID, productElementID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(AddRemoveDialogVisitor v) {
		v.visiOtherRemoveComponentDialog(this);
	}

}
