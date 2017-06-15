package ui;

import javax.swing.JDialog;

import common.Constants;
import ui.handler.UserManager;
import ui.userPanel.ActionHistoryDialogType;
import ui.userPanel.RegisterDialog;
import ui.userPanel.ViewActionHistoryDialog;
import ui.userPanel.ViewUserInfoDialog;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public abstract class LoggedInMainPortal extends LoggedInWindow {
	
	public static void startRelevantMainPortal(int userID){
		UserManager userManager = new UserManager();
		String authLevel = userManager.getUserAuthenticationLevel(userID);
		switch (authLevel) {
			case Constants.ADMIN:
				new AdminPortal(userID);
				break;
			case Constants.MANAGER:
				new ManagerPortal(userID);
				break;
			case Constants.CUSTOMER:
				new CustomerPortal(userID);
				break;
			case Constants.EMPLOYEE:
				new EmployeePanel(userID);
				break;
			default:
				new ManagerPortal(userID);
				break;
		}
	}
	
	public LoggedInMainPortal(int userID) {
		super(userID);
		
		
		setBounds(500, 500, 469, 468);
		
		JButton viewActionHistoryButton = new JButton("\u0645\u0634\u0627\u0647\u062F\u0647\u200C\u06CC \u0641\u0639\u0627\u0644\u06CC\u062A\u200C\u0647\u0627");
		viewActionHistoryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoggedInMainPortal.this.setVisible(false);
				new ViewActionHistoryDialog(userID, ActionHistoryDialogType.PERSONAL);
				LoggedInMainPortal.this.dispatchEvent(new WindowEvent(LoggedInMainPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		viewActionHistoryButton.setBounds(160, 0, 127, 23);
		getContentPane().add(viewActionHistoryButton);
		
		JButton viewUserInfoButton = new JButton("\u062A\u063A\u06CC\u06CC\u0631 \u0627\u0637\u0644\u0627\u0639\u0627\u062A \u06A9\u0627\u0631\u0628\u0631\u06CC");
		viewUserInfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoggedInMainPortal.this.setVisible(false);
				new ViewUserInfoDialog(userID);
				LoggedInMainPortal.this.dispatchEvent(new WindowEvent(LoggedInMainPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		viewUserInfoButton.setBounds(326, 0, 127, 23);
		getContentPane().add(viewUserInfoButton);
		setVisible(true);
	}
}
