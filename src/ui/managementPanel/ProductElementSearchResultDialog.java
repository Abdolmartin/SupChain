package ui.managementPanel;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.GeneralPESearchResultDialog;

public class ProductElementSearchResultDialog extends GeneralPESearchResultDialog {

	public ProductElementSearchResultDialog(int userID, List<JSONObject> results) {
		super(userID, results);
	}

	@Override
	protected void submitView() {
		if (table.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this, "باید یک مورد را انتخاب کنید!");
			return;
		}
		int productElementID = Integer.parseInt((String)table.getModel().
				getValueAt(table.getSelectedRow(), new JsonToJTableService().getColumnIndex(table, Constants.ID)));
		System.out.println(2);
		String productElementType = (String)table.getModel().getValueAt(table.getSelectedRow(), table.getColumn("type").getModelIndex());
		System.out.println(3);
		this.setVisible(false);
		new ProductElementViewDialog(userID, productElementID, productElementType);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
