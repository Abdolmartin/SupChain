package ui.salesPanel;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import ui.GeneralPESearchResultDialog;

public class FinalProductSearchResultDialog extends GeneralPESearchResultDialog {

	public FinalProductSearchResultDialog(int userID, ArrayList<JSONObject> results) {
		super(userID, results);
	}

	@Override
	protected void submitView() {
		int productElementID = (Integer)table.getModel().getValueAt(table.getSelectedRow(), table.getColumn("id").getModelIndex());
		this.setVisible(false);
		new FinalProductViewDialog(userID, productElementID);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
