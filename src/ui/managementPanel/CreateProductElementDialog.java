package ui.managementPanel;

import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;

import javax.swing.JButton;
import javax.swing.JTextField;

import common.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class CreateProductElementDialog extends LoggedInWindow {
	private JTextField nameField;
	private JTextField lowerBoundField;
	private JTextField upperBoundField;
	private JComboBox typeComboBox;
	private JTextArea descArea;
	private JCheckBox finalityCheckBox;

	public CreateProductElementDialog(int userID) {
		super(userID);
		
		nameField = new JTextField();
		nameField.setBounds(109, 88, 254, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(373, 91, 46, 14);
		getContentPane().add(label);
		
		lowerBoundField = new JTextField();
		lowerBoundField.setBounds(109, 114, 254, 20);
		getContentPane().add(lowerBoundField);
		lowerBoundField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u062D\u062F \u067E\u0627\u06CC\u06CC\u0646 \u0645\u0648\u062C\u0648\u062F\u06CC");
		lblNewLabel.setBounds(370, 110, 86, 14);
		getContentPane().add(lblNewLabel);
		
		upperBoundField = new JTextField();
		upperBoundField.setBounds(109, 138, 254, 20);
		getContentPane().add(upperBoundField);
		upperBoundField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u062D\u062F \u0628\u0627\u0644\u0627\u06CC \u0645\u0648\u062C\u0648\u062F\u06CC");
		label_1.setBounds(370, 141, 83, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u0646\u0648\u0639");
		label_2.setBounds(373, 166, 46, 14);
		getContentPane().add(label_2);
		
		typeComboBox = new JComboBox();
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {Constants.PRODUCT, Constants.COMPONENT}));
		typeComboBox.setBounds(109, 163, 254, 20);
		getContentPane().add(typeComboBox);
		
		descArea = new JTextArea();
		descArea.setBounds(109, 194, 254, 121);
		getContentPane().add(descArea);
		
		JLabel label_3 = new JLabel("\u062A\u0648\u0636\u06CC\u062D");
		label_3.setBounds(373, 191, 46, 14);
		getContentPane().add(label_3);
		
		finalityCheckBox = new JCheckBox("\u0646\u0647\u0627\u06CC\u06CC\u061F (\u0645\u062D\u0635\u0648\u0644)");
		finalityCheckBox.setBounds(233, 322, 130, 23);
		getContentPane().add(finalityCheckBox);
		
		JButton submitButton = new JButton("\u0627\u06CC\u062C\u0627\u062F \u0645\u0648\u0644\u0641\u0647/\u0645\u062D\u0635\u0648\u0644 \u062C\u062F\u06CC\u062F");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submit();
			}
		});
		submitButton.setBounds(47, 385, 151, 23);
		getContentPane().add(submitButton);
		
		JButton clearAllButton = new JButton("\u067E\u0627\u06A9 \u06A9\u0631\u062F\u0646 \u0627\u0637\u0644\u0627\u0639\u0627\u062A");
		clearAllButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAll();
			}
		});
		clearAllButton.setBounds(206, 385, 126, 23);
		getContentPane().add(clearAllButton);
		
		JButton cancelButton = new ReturnToMainButton(userID, this, "لغو فعالیت");
		cancelButton.setBounds(342, 385, 89, 23);
		getContentPane().add(cancelButton);
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
	}
	
	void submit(){
		String name = nameField.getText();
		int low, high;
		if (this.lowerBoundField.getText().equals(""))
			low = -1;
		else{
			try{
				low = Integer.valueOf(this.lowerBoundField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		
		if (this.upperBoundField.getText().equals(""))
			high = -1;
		else{
			try{
				high = Integer.valueOf(this.upperBoundField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		
		String type = (String)this.typeComboBox.getSelectedItem();
		String desc = this.descArea.getText();
		boolean finality = finalityCheckBox.isSelected();
		
		OrganisationManagementFacade orgManagementFacade = new OrganisationManagementFacade();
		String result = orgManagementFacade.createProductElement(type, name, low, high, desc, finality);
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
	
	void clearAll(){
		nameField.setText("");
		lowerBoundField.setText("");
		upperBoundField.setText("");
		descArea.setText("");
		finalityCheckBox.setSelected(false);
	}
}
