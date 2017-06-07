package ui;

import javax.swing.JDialog;
import javax.swing.JButton;

public abstract class LoggedInMainPortal extends JDialog {
	
	int userID;
	public LoggedInMainPortal(int userID) {
		this.userID = userID;
		
		getContentPane().setLayout(null);
		
		JButton logOutButton = new JButton("\u062E\u0631\u0648\u062C \u0627\u0632 \u0633\u0627\u0645\u0627\u0646\u0647");
		logOutButton.setBounds(340, 11, 123, 23);
		getContentPane().add(logOutButton);
	}
}
