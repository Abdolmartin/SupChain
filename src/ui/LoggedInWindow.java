package ui;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import ui.userPanel.LoginDialog;
import ui.userPanel.RegisterDialog;
import javax.swing.JButton;

public class LoggedInWindow extends JDialog {
	
	protected int userID;
	public LoggedInWindow(int userID) {
		this.userID = userID;
		getContentPane().setLayout(null);
		
		JButton button = new LogOutButton(userID, this);
		button.setBounds(0, 0, 112, 23);
		getContentPane().add(button);
	}

	public void goToInitial(){
		this.setVisible(false);
		new InitialPortal();
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
