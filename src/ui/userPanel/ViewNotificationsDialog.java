package ui.userPanel;

import ui.LoggedInMainPortal;
import ui.LoggedInWindow;
import ui.handler.UserManager;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import common.JsonToJTableService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ViewNotificationsDialog extends LoggedInWindow {
	private JTable table;

	public ViewNotificationsDialog(int userID) {
		super(userID);
		
		JButton returnButton = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647\u200C\u06CC \u0627\u0635\u0644\u06CC");
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewNotificationsDialog.this.setVisible(false);
				LoggedInMainPortal.startRelevantMainPortal(userID);
				ViewNotificationsDialog.this.dispatchEvent(new WindowEvent(ViewNotificationsDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		returnButton.setBounds(252, 0, 153, 23);
		getContentPane().add(returnButton);
		
		table = new JTable();
		table.setBounds(77, 59, 308, 250);
		getContentPane().add(table);
		
		load();
		
		setBounds(500, 500, 500, 500);
		setVisible(true);
	}
	
	void load(){
		UserManager userManager = new UserManager();
		ArrayList<JSONObject> notifsObj = userManager.getNotifications(userID);
		
		JSONObject firstJSON = null;
		try {
			firstJSON = notifsObj.get(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "موردی یافت نشد.");
		}
		if (firstJSON.containsKey("error")){
			this.goToInitial();
		} else{
			JsonToJTableService jsonToJTableService = new JsonToJTableService();
			this.table.setModel(jsonToJTableService.createJTableFromJSON(notifsObj));
		}
	}
}
