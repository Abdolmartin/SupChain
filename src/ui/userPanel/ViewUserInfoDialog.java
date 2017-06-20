package ui.userPanel;

import ui.AdminPortal;
import ui.LoggedInMainPortal;
import ui.LoggedInWindow;
import ui.handler.UserManager;
import userManagement.UserProfileCatalogue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import common.Constants;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class ViewUserInfoDialog extends LoggedInWindow {
	private JTextField usernameField;
	private JTextField newPassField;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField telephoneField;
	private JTextField prevPassField;

	public ViewUserInfoDialog(int userID) {
		super(userID);
		
		JLabel label = new JLabel("نام کاربری");
		label.setBounds(330, 61, 62, 14);
		getContentPane().add(label);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(126, 55, 188, 20);
		getContentPane().add(usernameField);
		
		newPassField = new JTextField();
		newPassField.setColumns(10);
		newPassField.setBounds(126, 86, 188, 20);
		getContentPane().add(newPassField);
		
		JLabel label_1 = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647\u200C\u06CC \u062C\u062F\u06CC\u062F");
		label_1.setBounds(330, 92, 86, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("نام");
		label_2.setBounds(330, 123, 46, 14);
		getContentPane().add(label_2);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(126, 117, 188, 20);
		getContentPane().add(nameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(126, 148, 188, 20);
		getContentPane().add(lastNameField);
		
		JLabel label_3 = new JLabel("نام خانوادگی");
		label_3.setBounds(330, 154, 62, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("ایمیل");
		label_4.setBounds(330, 185, 46, 14);
		getContentPane().add(label_4);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(126, 179, 188, 20);
		getContentPane().add(emailField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(34, 212, 280, 20);
		getContentPane().add(addressField);
		
		JLabel label_5 = new JLabel("آدرس");
		label_5.setBounds(330, 215, 46, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("شماره همراه");
		label_6.setBounds(330, 246, 86, 14);
		getContentPane().add(label_6);
		
		telephoneField = new JTextField();
		telephoneField.setColumns(10);
		telephoneField.setBounds(126, 243, 188, 20);
		getContentPane().add(telephoneField);
		
		prevPassField = new JTextField();
		prevPassField.setBounds(126, 295, 188, 20);
		getContentPane().add(prevPassField);
		prevPassField.setColumns(10);
		
		JLabel label_7 = new JLabel("گذرواژه‌ی قبلی");
		label_7.setBounds(330, 298, 86, 14);
		getContentPane().add(label_7);
		
		JButton cancelButton = new JButton("لغو فعالیت");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewUserInfoDialog.this.setVisible(false);
				LoggedInMainPortal.startRelevantMainPortal(userID);
				ViewUserInfoDialog.this.dispatchEvent(new WindowEvent(ViewUserInfoDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		cancelButton.setBounds(94, 350, 89, 23);
		getContentPane().add(cancelButton);
		
		JButton submitButton = new JButton("ثبت تغییرات");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submit();
			}
		});
		submitButton.setBounds(226, 350, 89, 23);
		getContentPane().add(submitButton);
		
		this.setBounds(500, 500, 500, 500);
		this.load();
		setVisible(true);
		
		
	}
	
	void load() {
		UserManager userManager = new UserManager();
		JSONObject infoObj = userManager.getInfo(this.userID);
		if (infoObj.containsKey("error")){
			this.goToInitial();
		}
		usernameField.setText((String)infoObj.get("username"));
		nameField.setText((String)infoObj.get("firstName"));
		lastNameField.setText((String)infoObj.get("lastName"));
		telephoneField.setText((String)infoObj.get("telephone"));
		emailField.setText((String)infoObj.get("email"));
		addressField.setText((String)infoObj.get("address"));
	}

	void submit(){
		String username = usernameField.getText();
		String newPass = newPassField.getText();
		String firstName = nameField.getText();
		String lastName = lastNameField.getText();
		String telephoneNumber = telephoneField.getText();
		String emailAddress = emailField.getText();
		String physicalAddress = addressField.getText();
		String oldPass = prevPassField.getText();
		
		UserManager userManager = new UserManager();
		String result = userManager.changeUserInfo(this.userID, newPass, firstName, 
				lastName, telephoneNumber, emailAddress, physicalAddress, oldPass);
		
		switch(result){
			case Constants.SUCCESS:
				JOptionPane.showMessageDialog(this, "اطلاعات با موفقیت تغییر کرد.");
				ViewUserInfoDialog.this.setVisible(false);
				new ViewUserInfoDialog(userID);
				ViewUserInfoDialog.this.dispatchEvent(new WindowEvent(ViewUserInfoDialog.this, WindowEvent.WINDOW_CLOSING));
				break;
			case Constants.NO_SUCH_ENTITY:
				JOptionPane.showMessageDialog(this, "SORRY, YOU DON'T EXIST");
				this.goToInitial();
				break;
			case Constants.INVALID_INFO:
				JOptionPane.showMessageDialog(this, "خطا در تغییر اطلاعات.");
				break;
			default:
				JOptionPane.showMessageDialog(this, Constants.GHAEDATAN);
		}
	}
}
