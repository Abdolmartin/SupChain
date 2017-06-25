package ui.managementPanel;

import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CreateSupplyPathDialog extends LoggedInWindow {
	private JTable componentsTable;
	private JTextField nameField;
	private JTextField supplierNameField;
	private JTextField telephoneField;
	private JTextField emailField;
	private JTextField addressField;

	public CreateSupplyPathDialog(int userID) {
		super(userID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 62, 300, 150);
		getContentPane().add(scrollPane);
		
		componentsTable = new JTable();
		scrollPane.setViewportView(componentsTable);
		
		nameField = new JTextField();
		nameField.setBounds(107, 223, 290, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(407, 226, 46, 14);
		getContentPane().add(label);
		
		supplierNameField = new JTextField();
		supplierNameField.setBounds(106, 252, 291, 20);
		getContentPane().add(supplierNameField);
		supplierNameField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0646\u0627\u0645 \u062A\u0627\u0645\u06CC\u0646\u200C\u06A9\u0646\u0646\u062F\u0647");
		label_1.setBounds(407, 255, 77, 14);
		getContentPane().add(label_1);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(107, 285, 290, 20);
		getContentPane().add(telephoneField);
		telephoneField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u062A\u0644\u0641\u0646");
		label_2.setBounds(407, 288, 46, 14);
		getContentPane().add(label_2);
		
		emailField = new JTextField();
		emailField.setBounds(106, 318, 291, 20);
		getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0627\u06CC\u0645\u06CC\u0644");
		label_3.setBounds(407, 321, 46, 14);
		getContentPane().add(label_3);
		
		addressField = new JTextField();
		addressField.setBounds(105, 349, 292, 20);
		getContentPane().add(addressField);
		addressField.setColumns(10);
		
		JLabel label_4 = new JLabel("\u0622\u062F\u0631\u0633");
		label_4.setBounds(407, 352, 46, 14);
		getContentPane().add(label_4);
		
		JButton submitButton = new JButton("\u062A\u0627\u06CC\u06CC\u062F \u0648 \u062B\u0628\u062A");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submit();
			}
		});
		submitButton.setBounds(107, 397, 89, 23);
		getContentPane().add(submitButton);
		
		JButton cancelButton = new ReturnToMainButton(userID, this, "لغو فعالیت");
		cancelButton.setBounds(261, 397, 89, 23);
		getContentPane().add(cancelButton);
		
		load();
		
		setVisible(true);
		setBounds(500, 500, 600, 500);
	}

	void submit() {
		String name = this.nameField.getText();
		String supplierName = this.supplierNameField.getText();
		String email = this.emailField.getText();
		String telephone = this.telephoneField.getText();
		String address = this.addressField.getText();
		int[] componentRows, componentIDs;
		componentRows = componentsTable.getSelectedRows();
		
		if (componentRows.length == 0){
			JOptionPane.showMessageDialog(this, "باید حداقل شامل یک مولفه باشد.");
			return;
		}
		
		JsonToJTableService j2tService = new JsonToJTableService();
		int idColumn = j2tService.getIDColumnIndex(componentsTable);
		
		componentIDs = new int[componentRows.length];
		
		for (int i=0;i<componentRows.length;i++){
			componentIDs[i] = Integer.parseInt((String)componentsTable.getModel().getValueAt(componentRows[i], idColumn));
		}
		
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		String result = organisationManagementFacade.createSupplyPath(supplierName,
				supplierName, componentIDs, email, telephone, address);
		
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
	
	void load(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		ArrayList<JSONObject> allComponents = organisationManagementFacade.productElementSearch(Constants.COMPONENT, "", 0, Double.MAX_VALUE, false, false);
		JsonToJTableService j2tService = new JsonToJTableService();
		componentsTable.setModel(j2tService.createJTableFromJSON(allComponents));
		j2tService.hideIDColumn(componentsTable);
	}

}
