package ui.managementPanel;

import ui.GeneralSearchDialog;
import ui.handler.OrganisationManagementFacade;
import ui.salesPanel.FinalProductSearchResultDialog;

import javax.swing.JTextField;

import org.json.simple.JSONObject;

import common.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class ProductElementSearchDialog extends GeneralSearchDialog {
	JComboBox<String> typeComboBox;
	JCheckBox inStockCheckbox;
	JCheckBox finalityCheckbox;
	
	public ProductElementSearchDialog(int userID) {
		super(userID);
		
		typeComboBox = new JComboBox();
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {Constants.ANY, Constants.PRODUCT, Constants.COMPONENT}));
		typeComboBox.setBounds(107, 197, 248, 20);
		getContentPane().add(typeComboBox);
		
		JLabel label = new JLabel("\u0646\u0648\u0639");
		label.setBounds(365, 200, 46, 14);
		getContentPane().add(label);
		
		inStockCheckbox = new JCheckBox("\u062F\u0627\u0631\u0627\u06CC \u0645\u0648\u062C\u0648\u062F\u06CC");
		inStockCheckbox.setBounds(258, 224, 97, 23);
		getContentPane().add(inStockCheckbox);
		
		finalityCheckbox = new JCheckBox("(\u0645\u062D\u0635\u0648\u0644) \u0646\u0647\u0627\u06CC\u06CC");
		finalityCheckbox.setBounds(258, 250, 97, 23);
		getContentPane().add(finalityCheckbox);
		
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
	}

	@Override
	protected void submitSearch() {
		String name = nameField.getText();
		double low, high;
		if (this.lowerPriceField.getText().equals(""))
			low = 0.0;
		else{
			try{
				low = Double.valueOf(this.lowerPriceField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		if (this.upperPriceField.getText().equals(""))
			high = Double.MAX_VALUE;
		else{
			try{
				high = Double.valueOf(this.upperPriceField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		String type = (String)this.typeComboBox.getSelectedItem();
		boolean inStock = inStockCheckbox.isSelected(), finality = finalityCheckbox.isSelected();
		OrganisationManagementFacade orgManagementFacade = new OrganisationManagementFacade();
		ArrayList<JSONObject> results = orgManagementFacade.productElementSearch(type, name, low, high, inStock, finality);
		this.setVisible(false);
		new ProductElementSearchResultDialog(userID, results);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
