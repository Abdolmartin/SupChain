package ui.userPanel;

import javax.swing.JDialog;
import javax.swing.JButton;

import ui.InitialPortal;
import ui.handler.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.Constants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class RegisterDialog extends JDialog {
	private JTextField usernameField;
	private JTextField passField;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField telephoneField;
	public RegisterDialog() {
		getContentPane().setLayout(null);
		
		JButton submitButton = new JButton("\u062B\u0628\u062A \u0646\u0627\u0645");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterDialog.this.submit();
			}
		});
		submitButton.setBounds(226, 319, 89, 23);
		getContentPane().add(submitButton);
		
		JButton clearButton = new JButton("\u067E\u0627\u06A9 \u06A9\u0631\u062F\u0646");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usernameField.setText("");
				passField.setText("");
				nameField.setText("");
				lastNameField.setText("");
				emailField.setText("");
				addressField.setText("");
				telephoneField.setText("");
			}
		});
		clearButton.setBounds(127, 319, 89, 23);
		getContentPane().add(clearButton);
		
		JButton returnToInitialButton = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647\u200C\u06CC \u0627\u0635\u0644\u06CC");
		returnToInitialButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterDialog.this.setVisible(false);
				new InitialPortal();
				RegisterDialog.this.dispatchEvent(new WindowEvent(RegisterDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		returnToInitialButton.setBounds(138, 11, 153, 23);
		getContentPane().add(returnToInitialButton);
		
		JLabel label = new JLabel("\u0647\u0645\u0647\u200C\u06CC \u0645\u0648\u0627\u0631\u062F \u0627\u062C\u0628\u0627\u0631\u06CC \u0647\u0633\u062A\u0646\u062F");
		label.setBounds(161, 45, 142, 14);
		getContentPane().add(label);
		
		usernameField = new JTextField();
		usernameField.setBounds(127, 83, 188, 20);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(127, 114, 188, 20);
		getContentPane().add(passField);
		passField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(127, 145, 188, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(127, 176, 188, 20);
		getContentPane().add(lastNameField);
		lastNameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(127, 207, 188, 20);
		getContentPane().add(emailField);
		emailField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(35, 240, 280, 20);
		getContentPane().add(addressField);
		addressField.setColumns(10);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(127, 271, 188, 20);
		getContentPane().add(telephoneField);
		telephoneField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC");
		label_1.setBounds(331, 89, 62, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647");
		label_2.setBounds(331, 120, 46, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u0646\u0627\u0645");
		label_3.setBounds(331, 151, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u0646\u0627\u0645 \u062E\u0627\u0646\u0648\u0627\u062F\u06AF\u06CC");
		label_4.setBounds(331, 182, 62, 14);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u0627\u06CC\u0645\u06CC\u0644");
		label_5.setBounds(331, 213, 46, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u0622\u062F\u0631\u0633");
		label_6.setBounds(331, 243, 46, 14);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u0634\u0645\u0627\u0631\u0647 \u0647\u0645\u0631\u0627\u0647");
		label_7.setBounds(331, 274, 86, 14);
		getContentPane().add(label_7);
		
		setBounds(500, 500, 500, 500);
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
		
		if (username.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || 
				telephoneNumber.equals("") || emailAddress.equals("") || physicalAddress.equals("")){
			JOptionPane.showMessageDialog(this, "Which part of \"all fields are required\" don't you understand?");
			return;
		}
		else{
			UserManager userManager = new UserManager();
			String result = userManager.createUser(Constants.CUSTOMER, username, password, firstName,
					lastName, telephoneNumber, emailAddress, physicalAddress, -1);
			switch(result){
				case Constants.SUCCESS:
					JOptionPane.showMessageDialog(this, "ثبت نام موفقیت‌آمیز بود.");
					RegisterDialog.this.setVisible(false);
					JDialog loginDialog = new LoginDialog();
					RegisterDialog.this.dispatchEvent(new WindowEvent(RegisterDialog.this, WindowEvent.WINDOW_CLOSING));
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
