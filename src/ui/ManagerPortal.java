package ui;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import ui.userPanel.ActionHistoryDialogType;
import ui.userPanel.ViewActionHistoryDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class ManagerPortal extends LoggedInMainPortal {

	public ManagerPortal(int userID) {
		super(userID);
		
		JButton button = new JButton("\u0641\u0647\u0631\u0633\u062A \u06A9\u0627\u0631\u0628\u0631\u0627\u0646 \u0648 \u0641\u0639\u0627\u0644\u06CC\u062A\u200C\u0647\u0627");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerPortal.this.setVisible(false);
				new ViewActionHistoryDialog(userID, ActionHistoryDialogType.SYSTEM);
				ManagerPortal.this.dispatchEvent(new WindowEvent(ManagerPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		button.setBounds(145, 22, 155, 23);
		getContentPane().add(button);
		
		
		this.pageComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
				PageTypes.ProductElementSearch.getCaption(),
				PageTypes.CreateProductElement.getCaption(),
				PageTypes.CreateProductionProcess.getCaption(),
				PageTypes.CreateSupplyPath.getCaption(),
				PageTypes.ViewOrdersList.getCaption()}));
		
		
		this.setBounds(500, 500, 500, 700);
		setVisible(true);
	}
}
