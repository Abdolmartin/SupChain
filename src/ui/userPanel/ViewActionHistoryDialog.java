package ui.userPanel;

import ui.LoggedInMainPortal;
import ui.LoggedInWindow;
import ui.handler.UserManager;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import common.JsonToJTableService;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JScrollPane;

public class ViewActionHistoryDialog extends LoggedInWindow {
	private JTable table;
	ActionHistoryDialogType dialogType;
	

	public ViewActionHistoryDialog(int userID, ActionHistoryDialogType dialogType) {
		super(userID);
		this.dialogType = dialogType;
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 81, 352, 309);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		load();
		
		setBounds(500, 500, 500, 500);
		setVisible(true);
	}
	
	void load(){
		UserManager userManager = new UserManager();
		ArrayList<JSONObject> logsObj;
		
		if (this.dialogType == ActionHistoryDialogType.PERSONAL)
			logsObj = userManager.getLogs(userID);
		else
			logsObj = userManager.getAllLogs();
		
		JSONObject firstJSON = null;
		try {
			firstJSON = logsObj.get(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "عمل ثبت‌شده‌ای وجود ندارد.");
		}
		if (firstJSON.containsKey("error")){
			this.goToInitial();
		} else{
			JsonToJTableService jsonToJTableService = new JsonToJTableService();
			this.table.setModel(jsonToJTableService.createJTableFromJSON(logsObj));
		}
	}
}
