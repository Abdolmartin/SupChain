package ui.managementPanel;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import ui.GeneralPESearchResultDialog;

public class ProductElementSearchResultDialog extends GeneralPESearchResultDialog {

	public ProductElementSearchResultDialog(int userID, ArrayList<JSONObject> results) {
		super(userID, results);
	}

	@Override
	protected void submitView() {
		int productElementID = (Integer)table.getModel().getValueAt(table.getSelectedRow(), table.getColumn("id").getModelIndex());
		String productElementType = (String)table.getModel().getValueAt(table.getSelectedRow(), table.getColumn("type").getModelIndex());
		this.setVisible(false);
		new ProductElementViewDialog(userID, productElementID, productElementType);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
