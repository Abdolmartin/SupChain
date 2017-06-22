package ui;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import ui.managementPanel.ProductElementViewDialog;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public abstract class GeneralSearchDialog extends LoggedInWindow {
	protected JTextField nameField;
	protected JTextField upperPriceField;
	protected JTextField lowerPriceField;
	
	public GeneralSearchDialog(int userID) {
		super(userID);
		
		JButton returnToMainButton = new ReturnToMainButton(this.userID, this, "بازگشت به صفحه‌ی اصلی");
		returnToMainButton.setBounds(202, 0, 153, 23);
		getContentPane().add(returnToMainButton);
		
		nameField = new JTextField();
		nameField.setBounds(107, 104, 248, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(365, 107, 46, 14);
		getContentPane().add(label);
		
		JButton submitButton = new JButton("\u062C\u0633\u062A\u062C\u0648");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submitSearch();
			}
		});
		submitButton.setBounds(202, 380, 89, 23);
		getContentPane().add(submitButton);
		
		upperPriceField = new JTextField();
		upperPriceField.setBounds(107, 135, 248, 20);
		getContentPane().add(upperPriceField);
		upperPriceField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u062D\u062F \u0628\u0627\u0644\u0627\u06CC \u0642\u06CC\u0645\u062A");
		label_1.setBounds(365, 138, 80, 14);
		getContentPane().add(label_1);
		
		lowerPriceField = new JTextField();
		lowerPriceField.setBounds(107, 166, 248, 20);
		getContentPane().add(lowerPriceField);
		lowerPriceField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u062D\u062F \u067E\u0627\u06CC\u06CC\u0646 \u0642\u06CC\u0645\u062A");
		label_2.setBounds(365, 169, 80, 14);
		getContentPane().add(label_2);
	}
	
	protected abstract void submitSearch();
}
