package ui.salesPanel;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import common.JsonToJTableService;
import ui.GeneralPESearchResultDialog;

public class FinalProductSearchResultDialog extends GeneralPESearchResultDialog {

	public FinalProductSearchResultDialog(int userID, ArrayList<JSONObject> results) {
		super(userID, results);
	}

	@Override
	protected void submitView() {
		if (table.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this, "باید یک مورد را انتخاب کنید!");
			return;
		}
		int productElementID = Integer.parseInt((String)table.getModel().getValueAt(table.getSelectedRow(), new JsonToJTableService().getIDColumnIndex(table)));
		this.setVisible(false);
		new FinalProductViewDialog(userID, productElementID);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
