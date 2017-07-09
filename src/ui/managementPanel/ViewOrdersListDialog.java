package ui.managementPanel;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.LoggedInWindow;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;

public class ViewOrdersListDialog extends LoggedInWindow {
	private JTable ordersTable;

	public ViewOrdersListDialog(int userID) {
		super(userID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 46, 424, 339);
		getContentPane().add(scrollPane);
		
		ordersTable = new JTable();
		scrollPane.setViewportView(ordersTable);
		
		JButton button = new ReturnToMainButton(userID, this, "بازگشت به صفحه اصلی");
		button.setBounds(192, 0, 156, 23);
		getContentPane().add(button);
		load();
		this.setVisible(true);
		this.setBounds(500, 500, 500, 500);
	}
	
	void load(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		List<JSONObject> result = organisationManagementFacade.getAllOrders();
		JsonToJTableService j2t = new JsonToJTableService();
		this.ordersTable.setModel(j2t.createJTableFromJSON(result));
		j2t.disableColSelection(ordersTable);
		j2t.hideTableColumn(ordersTable, Constants.ID);
	}
}
