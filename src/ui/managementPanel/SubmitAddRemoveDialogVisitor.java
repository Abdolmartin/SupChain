package ui.managementPanel;

import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import common.Constants;
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
	public void visiOtherRemoveProductDialog(OtherRemoveProductDialog otherRemoveProductDialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visiOtherRemoveComponentDialog(OtherRemoveComponentDialog otherRemoveComponentDialog) {
		// TODO Auto-generated method stub
		
	}
	
}
