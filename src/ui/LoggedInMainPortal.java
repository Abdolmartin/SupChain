package ui;

import javax.swing.JDialog;

import ui.managementPanel.CreateProductElementDialog;
import ui.managementPanel.ProductElementSearchDialog;
import ui.salesPanel.FinalProductSearchDialog;
import ui.userPanel.ActionHistoryDialogType;
import ui.userPanel.CreateUserDialog;
import ui.userPanel.RegisterDialog;
import ui.userPanel.ViewActionHistoryDialog;
import ui.userPanel.ViewNotificationsDialog;
import ui.userPanel.ViewUserInfoDialog;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public abstract class LoggedInMainPortal extends LoggedInWindow {
	protected JComboBox pageComboBox;
	
	public LoggedInMainPortal(int userID) {
		super(userID);
		
		
		setBounds(500, 500, 547, 525);
		
		JButton viewActionHistoryButton = new JButton("\u0645\u0634\u0627\u0647\u062F\u0647\u200C\u06CC \u0641\u0639\u0627\u0644\u06CC\u062A\u200C\u0647\u0627");
		viewActionHistoryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoggedInMainPortal.this.setVisible(false);
				new ViewActionHistoryDialog(userID, ActionHistoryDialogType.PERSONAL);
				LoggedInMainPortal.this.dispatchEvent(new WindowEvent(LoggedInMainPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		viewActionHistoryButton.setBounds(275, 0, 127, 23);
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
		viewUserInfoButton.setBounds(404, 0, 127, 23);
		getContentPane().add(viewUserInfoButton);
		
		JButton viewNotificationsButton = new JButton("\u0645\u0634\u0627\u0647\u062F\u0647\u200C\u06CC \u067E\u06CC\u0627\u0645\u200C\u0647\u0627");
		viewNotificationsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoggedInMainPortal.this.setVisible(false);
				new ViewNotificationsDialog(userID);
				LoggedInMainPortal.this.dispatchEvent(new WindowEvent(LoggedInMainPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		viewNotificationsButton.setBounds(159, 0, 113, 23);
		getContentPane().add(viewNotificationsButton);
		
		pageComboBox = new JComboBox();
		pageComboBox.setBounds(129, 161, 295, 23);
		getContentPane().add(pageComboBox);
		
		JLabel label = new JLabel("\u0635\u0641\u062D\u0647\u200C\u06CC \u0645\u0648\u0631\u062F \u0646\u0638\u0631 \u0631\u0627 \u0627\u0646\u062A\u062E\u0627\u0628 \u06A9\u0646\u06CC\u062F");
		label.setBounds(196, 136, 201, 14);
		getContentPane().add(label);
		
		JButton goButton = new JButton("\u0628\u0631\u0648");
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToSelectedPage();
			}
		});
		goButton.setBounds(232, 195, 89, 23);
		getContentPane().add(goButton);
		setVisible(true);
	}

	protected void goToSelectedPage(){
		String selection = (String)pageComboBox.getSelectedItem();
		this.setVisible(false);
		if (selection.equals(PageTypes.CreateProductElement.getCaption())){
			new CreateProductElementDialog(userID);
		} else if (selection.equals(PageTypes.FinalProductSearch.getCaption())){
			new FinalProductSearchDialog(userID);
		} else if (selection.equals(PageTypes.ProductElementSearch.getCaption())){
			new ProductElementSearchDialog(userID);
		} else if (selection.equals(PageTypes.CreateUser.getCaption())){
			new CreateUserDialog(userID);
		} else { //DEFAULT, USED IN CASE OF ERRORS
			new MainPortalRedirectService().startRelevantMainPortal(userID);
		}
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
