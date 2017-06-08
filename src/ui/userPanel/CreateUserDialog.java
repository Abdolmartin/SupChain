package ui.userPanel;

import ui.AdminPortal;
import ui.InitialPortal;
import ui.LoggedInMainPortal;
import ui.LoggedInWindow;
import ui.handler.UserManager;

import javax.swing.JTextField;

import common.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class CreateUserDialog extends LoggedInWindow {
	private JTextField usernameField;
	private JTextField passField;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField telephoneField;
	private JComboBox<String> roleComboBox;
	
	public CreateUserDialog(int userID) {
		super(userID);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(117, 42, 188, 20);
		getContentPane().add(usernameField);
		
		JLabel label = new JLabel("نام کاربری");
		label.setBounds(321, 48, 62, 14);
		getContentPane().add(label);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(117, 73, 188, 20);
		getContentPane().add(passField);
		
		JLabel label_1 = new JLabel("گذرواژه");
		label_1.setBounds(321, 79, 46, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("نام");
		label_2.setBounds(321, 110, 46, 14);
		getContentPane().add(label_2);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(117, 104, 188, 20);
		getContentPane().add(nameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(117, 135, 188, 20);
		getContentPane().add(lastNameField);
		
		JLabel label_3 = new JLabel("نام خانوادگی");
		label_3.setBounds(321, 141, 62, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("ایمیل");
		label_4.setBounds(321, 172, 46, 14);
		getContentPane().add(label_4);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(117, 166, 188, 20);
		getContentPane().add(emailField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(25, 199, 280, 20);
		getContentPane().add(addressField);
		
		JLabel label_5 = new JLabel("آدرس");
		label_5.setBounds(321, 202, 46, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("شماره همراه");
		label_6.setBounds(321, 233, 86, 14);
		getContentPane().add(label_6);
		
		telephoneField = new JTextField();
		telephoneField.setColumns(10);
		telephoneField.setBounds(117, 230, 188, 20);
		getContentPane().add(telephoneField);
		
		JButton submitButton = new JButton("ایجاد کاربر جدید");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submit();
			}
		});
		submitButton.setBounds(224, 353, 106, 23);
		getContentPane().add(submitButton);
		
		JButton cancelButton = new JButton("لغو عملیات");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateUserDialog.this.setVisible(false);
				new AdminPortal(userID);
				CreateUserDialog.this.dispatchEvent(new WindowEvent(CreateUserDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		cancelButton.setBounds(107, 353, 89, 23);
		getContentPane().add(cancelButton);
		
		String[] roles = {Constants.MANAGER, Constants.EMPLOYEE, Constants.ADMIN,
				Constants.PRODUCTION_MANAGER, Constants.WAREHOUSE_MANAGER};
		
		roleComboBox = new JComboBox(roles);
		roleComboBox.setBounds(117, 261, 188, 20);
		getContentPane().add(roleComboBox);
		
		JLabel label_7 = new JLabel("نقش");
		label_7.setBounds(321, 264, 46, 14);
		getContentPane().add(label_7);
		setVisible(true);
	}
	
	void submit(){
		String username = usernameField.getText();
		String password = passField.getText();
		String firstName = nameField.getText();
		String lastName = lastNameField.getText();
		String telephoneNumber = telephoneField.getText();
		String emailAddress = emailField.getText();
		String physicalAddress = addressField.getText();
		String role = (String)roleComboBox.getSelectedItem();
		
		if (username.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || 
				telephoneNumber.equals("") || emailAddress.equals("") || physicalAddress.equals("")){
			JOptionPane.showMessageDialog(this, "Which part of \"all fields are required\" don't you understand?");
			return;
		}
		else{
			UserManager userManager = new UserManager();
			String result = userManager.createUser(role, username, password, firstName,
					lastName, telephoneNumber, emailAddress, physicalAddress, -1);
			switch(result){
				case Constants.SUCCESS:
					JOptionPane.showMessageDialog(this, "ایجاد موفقیت آمیز بود.");
					CreateUserDialog.this.setVisible(false);
					LoggedInMainPortal.startRelevantMainPortal(userID);
					CreateUserDialog.this.dispatchEvent(new WindowEvent(CreateUserDialog.this, WindowEvent.WINDOW_CLOSING));
					break;
				case Constants.INVALID_INFO:
					JOptionPane.showMessageDialog(this, "اطلاعات با قوانین سازگار نیست.");
					break;
				default:
					JOptionPane.showMessageDialog(this, Constants.GHAEDATAN);
			}
		}
	}
}
