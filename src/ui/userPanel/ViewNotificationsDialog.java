package ui.userPanel;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import ui.LoggedInWindow;
import ui.ReturnToMainButton;
import ui.handler.UserFacade;

public class ViewNotificationsDialog extends LoggedInWindow {
	private JTable table;

	public ViewNotificationsDialog(int userID) {
		super(userID);
		
		JButton returnButton = new ReturnToMainButton(userID, this, "بازگشت به صفحه‌ی اصلی");
		returnButton.setBounds(252, 0, 153, 23);
		getContentPane().add(returnButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 88, 357, 247);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		load();
		
		setBounds(500, 500, 500, 500);
		setVisible(true);
	}
	
	void load(){
		UserFacade userManager = new UserFacade();
		List<JSONObject> notifsObj = userManager.getNotifications(userID);
		
		JSONObject firstJSON = null;
		try {
			firstJSON = notifsObj.get(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "موردی یافت نشد.");
			table.setModel(new DefaultTableModel());
			return;
		}
		if (firstJSON.containsKey("error")){
			this.goToInitial();
		} else{
			JsonToJTableService jsonToJTableService = new JsonToJTableService();
			this.table.setModel(jsonToJTableService.createJTableFromJSON(notifsObj));
			jsonToJTableService.hideTableColumn(table, Constants.ID);
			table.setColumnSelectionAllowed(false);
		    table.setRowSelectionAllowed(true);
		}
	}
}
