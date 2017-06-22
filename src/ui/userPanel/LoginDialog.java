package ui.userPanel;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;

import common.Constants;
import ui.AdminPortal;
import ui.CustomerPortal;
import ui.EmployeePanel;
import ui.InitialPortal;
import ui.MainPortalRedirectService;
import ui.ManagerPortal;
import ui.handler.UserFacade;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

public class LoginDialog extends JDialog {
	private JTextField usernameField;
	private JTextField passwordField;
	
	public LoginDialog() {
		setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton submitButton = new JButton("\u0648\u0631\u0648\u062F \u0628\u0647 \u0633\u0627\u0645\u0627\u0646\u0647");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginDialog.this.submit();
			}
		});
		submitButton.setBounds(170, 278, 112, 23);
		getContentPane().add(submitButton);
		
		usernameField = new JTextField();
		usernameField.setBounds(139, 146, 160, 20);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(139, 177, 160, 20);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC");
		label.setBounds(309, 149, 46, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647");
		label_1.setBounds(309, 180, 46, 14);
		getContentPane().add(label_1);
		
		JButton returnToInitialButton = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647\u200C\u06CC \u0627\u0635\u0644\u06CC");
		returnToInitialButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginDialog.this.setVisible(false);
				new InitialPortal();
				LoginDialog.this.dispatchEvent(new WindowEvent(LoginDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		returnToInitialButton.setBounds(146, 11, 153, 23);
		getContentPane().add(returnToInitialButton);
		setBounds(500, 500, 470, 382);
		setVisible(true);
	}
	
	void submit(){
		UserFacade userManager = new UserFacade();
		String username = usernameField.getText();
		String password = passwordField.getText();
		String result = userManager.logIn(username, password);
		switch(result){
			case Constants.SUCCESS:
				int userID = userManager.getUserID(username);
				LoginDialog.this.setVisible(false);
				new MainPortalRedirectService().startRelevantMainPortal(userID);
				LoginDialog.this.dispatchEvent(new WindowEvent(LoginDialog.this, WindowEvent.WINDOW_CLOSING));
				break;
			default:
				JOptionPane.showMessageDialog(this, "نام کاربری یا رمز عبور اشتباه است.");
				break;
		}
		
	}
	
}
