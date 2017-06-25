package ui.managementPanel;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class CreateProductionProcessDialog extends LoggedInWindow {
	private JTable inputsTable;
	private JTable outputsTable;
	private JTextField nameField;
	private JTextArea deptsTextArea;

	public CreateProductionProcessDialog(int userID) {
		super(userID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 61, 184, 168);
		getContentPane().add(scrollPane);
		
		inputsTable = new JTable();
		scrollPane.setViewportView(inputsTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(252, 61, 184, 168);
		getContentPane().add(scrollPane_1);
		
		outputsTable = new JTable();
		scrollPane_1.setViewportView(outputsTable);
		
		nameField = new JTextField();
		nameField.setBounds(93, 255, 238, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(341, 258, 46, 14);
		getContentPane().add(label);
		
		deptsTextArea = new JTextArea();
		deptsTextArea.setBounds(93, 289, 238, 98);
		getContentPane().add(deptsTextArea);
		
		JLabel label_1 = new JLabel("\u062F\u067E\u0627\u0631\u062A\u0645\u0627\u0646\u200C\u0647\u0627\u06CC \u062F\u062E\u06CC\u0644 (\u0647\u0631 \u0633\u0637\u0631 \u06CC\u06A9 \u062F\u067E\u0627\u0631\u062A\u0645\u0627\u0646)");
		label_1.setBounds(336, 286, 215, 30);
		getContentPane().add(label_1);
		
		JButton submitButton = new JButton("\u062A\u0627\u06CC\u06CC\u062F \u0648 \u0627\u06CC\u062C\u0627\u062F");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submit();
			}
		});
		submitButton.setBounds(103, 398, 89, 23);
		getContentPane().add(submitButton);
		
		JButton cancelButton = new ReturnToMainButton(userID, this, "لغو فعالیت");
		cancelButton.setBounds(242, 398, 89, 23);
		getContentPane().add(cancelButton);
		
		load();
		
		this.setBounds(500,500,700,500);
		this.setVisible(true);
	}
	
	void load(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		ArrayList<JSONObject> allProductElements = organisationManagementFacade.productElementSearch(Constants.ANY, "", 0, Double.MAX_VALUE, false, false);
		JsonToJTableService j2tService = new JsonToJTableService();
		inputsTable.setModel(j2tService.createJTableFromJSON(allProductElements));
		outputsTable.setModel(j2tService.createJTableFromJSON(allProductElements));
		j2tService.hideTableColumn(inputsTable, Constants.ID);
		j2tService.hideTableColumn(outputsTable, Constants.ID);
		j2tService.disableColSelection(inputsTable);
		j2tService.disableColSelection(outputsTable);
	}
	
	void submit(){
		String name = this.nameField.getText();
		String[] depts = this.deptsTextArea.getText().split("\r\n|\n");
		int[] inputRows, outputRows, inputIDs, outputIDs;
		inputRows = inputsTable.getSelectedRows();
		outputRows = outputsTable.getSelectedRows();
		
		if (outputRows.length == 0){
			JOptionPane.showMessageDialog(this, "باید حتما خروجی داشته باشد!");
			return;
		}
		
		JsonToJTableService j2tService = new JsonToJTableService();
		int idColumn = j2tService.getColumnIndex(inputsTable, Constants.ID);
		
		inputIDs = new int[inputRows.length];
		outputIDs = new int[outputRows.length];
		
		for (int i=0;i<inputRows.length;i++){
			inputIDs[i] = Integer.parseInt((String)inputsTable.getModel().getValueAt(inputRows[i], idColumn));
		}
		
		idColumn = j2tService.getColumnIndex(outputsTable, Constants.ID);
		
		for (int i=0;i<outputRows.length;i++){
			outputIDs[i] = Integer.parseInt((String)outputsTable.getModel().getValueAt(outputRows[i], idColumn));
		}
		
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		String result = organisationManagementFacade.createProductionProcess(inputIDs, outputIDs, name, depts);
		if (result.equals(Constants.SUCCESS)){
			JOptionPane.showMessageDialog(this, "عملیات موفق");
			this.setVisible(false);
			new MainPortalRedirectService().startRelevantMainPortal(this.userID);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else{
			JOptionPane.showMessageDialog(this, "عملیات ناموفق. لطفا دوباره تلاش کنید.");
		}
	}
}
