package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;

import ui.handler.UserFacade;
import ui.userPanel.LoginDialog;

public class LogOutButton extends JButton {
	private int userID;
	private LoggedInWindow parentWindow;
	public LogOutButton(int userID, LoggedInWindow parentWindow) {
		super();
		setText("\u062E\u0631\u0648\u062C \u0627\u0632 \u0633\u0627\u0645\u0627\u0646\u0647");
		this.userID = userID;
		this.parentWindow = parentWindow;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserFacade().logOut(userID);
				parentWindow.goToInitial();
			}
		});
	}
	
	
}
