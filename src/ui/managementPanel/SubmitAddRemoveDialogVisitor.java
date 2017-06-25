package ui.managementPanel;

import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import common.Constants;
import common.JsonToJTableService;
import salesManagement.UnAvailableItemStatus;
import ui.MainPortalRedirectService;
import ui.handler.OrganisationManagementFacade;

public class SubmitAddRemoveDialogVisitor extends AddRemoveDialogVisitor {

	@Override
	public void visitOtherAddProductDialog(OtherAddProductDialog otherAddProductDialog) {
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		int count = (Integer) otherAddProductDialog.getCountSpinner().getValue();
		double price = 0;
		try{
			price = Double.parseDouble(otherAddProductDialog.getPriceField().getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(otherAddProductDialog, "قیمت عدده حاجی.");
			return;
		}
		String result = organisationManagementFacade.
				addProductElementInventory(otherAddProductDialog.productElementID, count, price);
		if (result.equals(Constants.SUCCESS)){
			otherAddProductDialog.succesAndToMain();		}
		else{
			JOptionPane.showMessageDialog(otherAddProductDialog, "عملیات ناموفق. لطفا دوباره تلاش کنید.");
		}
	}

	@Override
	public void visitOtherAddComponentDialog(OtherAddComponentDialog otherAddComponentDialog) {
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		int count = (Integer) otherAddComponentDialog.getCountSpinner().getValue();
		double price = 0;
		try{
			price = Double.parseDouble(otherAddComponentDialog.getPriceField().getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(otherAddComponentDialog, "قیمت عدده حاجی.");
			return;
		}
		String result = organisationManagementFacade.
				addProductElementInventory(otherAddComponentDialog.productElementID, count, price);
		if (result.equals(Constants.SUCCESS)){
			otherAddComponentDialog.succesAndToMain();		}
		else{
			JOptionPane.showMessageDialog(otherAddComponentDialog, "عملیات ناموفق. لطفا دوباره تلاش کنید.");
		}
	}

	@Override
	public void visitOtherRemoveProductDialog(OtherRemoveProductDialog otherRemoveProductDialog) {
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		int[] itemRows = otherRemoveProductDialog.getItemsTable().getSelectedRows(), itemIDs;
		itemIDs = new int[itemRows.length];
		JsonToJTableService j2t = new JsonToJTableService();
		int idColumn = j2t.getColumnIndex(otherRemoveProductDialog.getItemsTable(), Constants.ID);
		for (int i=0;i<itemRows.length;i++){
			itemIDs[i] = Integer.parseInt((String)otherRemoveProductDialog.
					getItemsTable().getModel().getValueAt(itemRows[i], idColumn));
			System.out.println(itemIDs[i]);
		}
		String result = organisationManagementFacade.changeItemStati(otherRemoveProductDialog.
				productElementID, itemIDs, new UnAvailableItemStatus(-1));
		if (result.equals(Constants.SUCCESS)){
			otherRemoveProductDialog.succesAndToMain();		}
		else{
			JOptionPane.showMessageDialog(otherRemoveProductDialog, "عملیات ناموفق. لطفا دوباره تلاش کنید.");
		}
	}

	@Override
	public void visitOtherRemoveComponentDialog(OtherRemoveComponentDialog otherRemoveComponentDialog) {
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		int[] itemRows = otherRemoveComponentDialog.getItemsTable().getSelectedRows(), itemIDs;
		itemIDs = new int[itemRows.length];
		JsonToJTableService j2t = new JsonToJTableService();
		int idColumn = j2t.getColumnIndex(otherRemoveComponentDialog.getItemsTable(), Constants.ID);
		for (int i=0;i<itemRows.length;i++)
			itemIDs[i] = Integer.parseInt((String)otherRemoveComponentDialog.
					getItemsTable().getModel().getValueAt(itemRows[i], idColumn));
		String result = organisationManagementFacade.changeItemStati(otherRemoveComponentDialog.
				productElementID, itemIDs, new UnAvailableItemStatus(-1));
		if (result.equals(Constants.SUCCESS)){
			otherRemoveComponentDialog.succesAndToMain();
		}
		else{
			JOptionPane.showMessageDialog(otherRemoveComponentDialog, "عملیات ناموفق. لطفا دوباره تلاش کنید.");
		}
	}
	
}
