package ui.managementPanel;

import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.LoggedInWindow;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ViewProductionProcessListDialog extends LoggedInWindow {
	private JTable processesTable;

	public ViewProductionProcessListDialog(int userID) {
		super(userID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 53, 421, 368);
		getContentPane().add(scrollPane);
		
		processesTable = new JTable();
		scrollPane.setViewportView(processesTable);
		
		JButton returnButton = new ReturnToMainButton(userID, this, "بازگشت به صفحه‌ی اصلی");
		returnButton.setBounds(187, 0, 89, 23);
		getContentPane().add(returnButton);
		load();
		this.setVisible(true);
		this.setBounds(500, 500, 500, 500);
	}
	
	void load(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		List<JSONObject> result = organisationManagementFacade.getAllProcesses();
		JsonToJTableService j2t = new JsonToJTableService();
		this.processesTable.setModel(j2t.createJTableFromJSON(result));
		j2t.disableColSelection(processesTable);
		j2t.hideTableColumn(processesTable, Constants.ID);
	}
}
