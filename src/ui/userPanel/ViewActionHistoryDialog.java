package ui.userPanel;

import ui.LoggedInMainPortal;
import ui.LoggedInWindow;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class ViewActionHistoryDialog extends LoggedInWindow {
	private JTable table;

	public ViewActionHistoryDialog(int userID) {
		super(userID);
		
		table = new JTable();
		table.setBounds(63, 75, 352, 253);
		getContentPane().add(table);
		
		JButton button = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647 \u0627\u0635\u0644\u06CC");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ViewActionHistoryDialog.this.setVisible(false);
				LoggedInMainPortal.startRelevantMainPortal(userID);
				ViewActionHistoryDialog.this.dispatchEvent(new WindowEvent(ViewActionHistoryDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		button.setBounds(221, 0, 169, 23);
		getContentPane().add(button);
		
		load();
		
		setBounds(500, 500, 500, 500);
		setVisible(true);
	}
	void load(){
		
	}
}
