package ui;

import javax.swing.JButton;

import ui.managementPanel.ProductElementViewDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class ReturnToMainButton extends JButton {
	private int userID;
	private LoggedInWindow parentWindow;
	public ReturnToMainButton(int userID, LoggedInWindow parentWindow, String text) {
		super();
		this.userID = userID;
		this.parentWindow = parentWindow;
		setText(text);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnToMainButton.this.parentWindow.setVisible(false);
				new MainPortalRedirectService().startRelevantMainPortal(ReturnToMainButton.this.userID);
				ReturnToMainButton.this.parentWindow.dispatchEvent(new WindowEvent(ReturnToMainButton.this.parentWindow,
						WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
}
