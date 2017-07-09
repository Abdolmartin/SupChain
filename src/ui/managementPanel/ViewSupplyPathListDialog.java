package ui.managementPanel;

import ui.LoggedInWindow;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;

public class ViewSupplyPathListDialog extends LoggedInWindow {
	private JTable pathsTable;

	public ViewSupplyPathListDialog(int userID) {
		super(userID);
		
		JButton returnButton = new ReturnToMainButton(userID, this, "بازگشت به صفحه‌ی اصلی");
		returnButton.setBounds(174, 0, 89, 23);
		getContentPane().add(returnButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 57, 434, 369);
		getContentPane().add(scrollPane);
		
		pathsTable = new JTable();
		scrollPane.setViewportView(pathsTable);
		
		load();
		this.setVisible(true);
		this.setBounds(500, 500, 500, 500);
	}
	
	void load(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		List<JSONObject> result = organisationManagementFacade.getAllSupplyPaths();
		JsonToJTableService j2t = new JsonToJTableService();
		this.pathsTable.setModel(j2t.createJTableFromJSON(result));
		j2t.disableColSelection(pathsTable);
		j2t.hideTableColumn(pathsTable, Constants.ID);
	}

}
