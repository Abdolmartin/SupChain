package ui.managementPanel;

import ui.LoggedInWindow;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddRemoveItemDialog extends LoggedInWindow {
	private JComboBox addProductComboBox;
	private JComboBox addComponentComboBox;
	private JComboBox removeProductComboBox;
	private JComboBox removeComponentComboBox;
	private int productElementID;

	public AddRemoveItemDialog(int userID, int productElementID) {
		super(userID);
		this.productElementID = productElementID;
		
		JButton addProductButton = new JButton("\u0627\u0641\u0632\u0627\u06CC\u0634 \u0645\u0648\u062C\u0648\u062F\u06CC \u0645\u062D\u0635\u0648\u0644");
		addProductButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		addProductButton.setBounds(154, 115, 163, 23);
		getContentPane().add(addProductButton);
		
		JButton addComponentButton = new JButton("\u0627\u0641\u0632\u0627\u06CC\u0634 \u0645\u0648\u062C\u0648\u062F\u06CC \u0645\u0648\u0644\u0641\u0647");
		addComponentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		addComponentButton.setBounds(154, 149, 163, 23);
		getContentPane().add(addComponentButton);
		
		JButton removeProductButton = new JButton("\u06A9\u0627\u0647\u0634 \u0645\u0648\u062C\u0648\u062F\u06CC \u0645\u062D\u0635\u0648\u0644");
		removeProductButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		removeProductButton.setBounds(154, 183, 163, 23);
		getContentPane().add(removeProductButton);
		
		JButton removeComponentButton = new JButton("\u06A9\u0627\u0647\u0634 \u0645\u0648\u062C\u0648\u062F\u06CC \u0645\u0648\u0644\u0641\u0647");
		removeComponentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		removeComponentButton.setBounds(154, 217, 163, 23);
		getContentPane().add(removeComponentButton);
		
		addProductComboBox = new JComboBox();
		addProductComboBox.setModel(new DefaultComboBoxModel(new String[] {"production", "other"}));
		addProductComboBox.setBounds(327, 116, 179, 22);
		getContentPane().add(addProductComboBox);
		
		addComponentComboBox = new JComboBox();
		addComponentComboBox.setModel(new DefaultComboBoxModel(new String[] {"deliverOrder", "other"}));
		addComponentComboBox.setBounds(327, 150, 179, 22);
		getContentPane().add(addComponentComboBox);
		
		removeProductComboBox = new JComboBox();
		removeProductComboBox.setModel(new DefaultComboBoxModel(new String[] {"production", "deliverOrder", "other"}));
		removeProductComboBox.setBounds(327, 184, 179, 22);
		getContentPane().add(removeProductComboBox);
		
		removeComponentComboBox = new JComboBox();
		removeComponentComboBox.setModel(new DefaultComboBoxModel(new String[] {"production", "other"}));
		removeComponentComboBox.setBounds(327, 218, 179, 20);
		getContentPane().add(removeComponentComboBox);
		
		this.setVisible(true);
		this.setBounds(500, 500, 700, 500);
		
	}
}
