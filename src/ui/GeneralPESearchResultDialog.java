package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import common.JsonToJTableService;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class GeneralPESearchResultDialog extends LoggedInWindow {
	protected ArrayList<JSONObject> results;
	protected JTable table;
	
	public GeneralPESearchResultDialog(int userID, ArrayList<JSONObject> results) {
		super(userID);
		this.results = results;
		
		JButton button = new ReturnToMainButton(this.userID, this, "بازگشت به صفحه‌ی اصلی");
		button.setBounds(221, 0, 179, 23);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 64, 387, 326);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton viewButton = new JButton("\u0645\u0634\u0627\u0647\u062F\u0647");
		viewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitView();
			}
		});
		viewButton.setBounds(202, 401, 89, 23);
		getContentPane().add(viewButton);
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
	}
	
	void load(){
		JsonToJTableService j2tService = new JsonToJTableService();
		DefaultTableModel model = j2tService.createJTableFromJSON(results);
		this.table.setModel(model);
		j2tService.hideIDColumn(table);
		table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
	}
	
	protected abstract void submitView();
}
