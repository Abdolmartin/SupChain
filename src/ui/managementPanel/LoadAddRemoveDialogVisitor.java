package ui.managementPanel;

import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.handler.OrganisationManagementFacade;

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
	public void visitOtherRemoveProductDialog(OtherRemoveProductDialog otherRemoveProductDialog) {
		otherRemoveProductDialog.setTitle("کاهش موجودی محصول - سایر");
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		List<JSONObject> jsonList = organisationManagementFacade.
				getProductElementItems(otherRemoveProductDialog.productElementID);
		JsonToJTableService j2t = new JsonToJTableService();
		otherRemoveProductDialog.getItemsTable().setModel(j2t.createJTableFromJSON(jsonList));
		j2t.hideTableColumn(otherRemoveProductDialog.getItemsTable(), Constants.ID);
		j2t.hideTableColumn(otherRemoveProductDialog.getItemsTable(), "typeID");
		j2t.disableColSelection(otherRemoveProductDialog.getItemsTable());
	}

	@Override
	public void visitOtherRemoveComponentDialog(OtherRemoveComponentDialog otherRemoveComponentDialog) {
		otherRemoveComponentDialog.setTitle("کاهش موجودی مولفه - سایر");
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		List<JSONObject> jsonList = organisationManagementFacade.
				getProductElementItems(otherRemoveComponentDialog.productElementID);
		JsonToJTableService j2t = new JsonToJTableService();
		otherRemoveComponentDialog.getItemsTable().setModel(j2t.createJTableFromJSON(jsonList));
		j2t.hideTableColumn(otherRemoveComponentDialog.getItemsTable(), Constants.ID);
		j2t.hideTableColumn(otherRemoveComponentDialog.getItemsTable(), "typeID");
		j2t.disableColSelection(otherRemoveComponentDialog.getItemsTable());
	}

}
