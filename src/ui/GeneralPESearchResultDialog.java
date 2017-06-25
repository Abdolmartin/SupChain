package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class GeneralPESearchResultDialog extends LoggedInWindow {
	protected List<JSONObject> results;
	protected JTable table;
	
	public GeneralPESearchResultDialog(int userID, List<JSONObject> results2) {
		super(userID);
		this.results = results2;
		
		JButton button = new ReturnToMainButton(this.userID, this, "بازگشت به صفحه‌ی اصلی");
		button.setBounds(221, 0, 179, 23);
		getContentPane().add(button);
		
		JButton viewButton = new JButton("\u0645\u0634\u0627\u0647\u062F\u0647");
		viewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitView();
			}
		});
		viewButton.setBounds(202, 401, 89, 23);
		getContentPane().add(viewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 87, 332, 255);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		load();
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
	}
	
	void load(){
		JsonToJTableService j2tService = new JsonToJTableService();
		DefaultTableModel model = j2tService.createJTableFromJSON(results);
		this.table.setModel(model);
		j2tService.hideTableColumn(table, Constants.ID);
		j2tService.disableColSelection(table);
	}
	
	protected abstract void submitView();
}
