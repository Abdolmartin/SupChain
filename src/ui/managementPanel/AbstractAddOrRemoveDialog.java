package ui.managementPanel;

import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public abstract class AbstractAddOrRemoveDialog extends LoggedInWindow {
	protected int productElementID;
	protected JTextField productElementNameField;

	public AbstractAddOrRemoveDialog(int userID, int productElementID) {
		super(userID);
		this.productElementID = productElementID;
		
		JButton button = new ReturnToMainButton(userID, this, "لغو فعالیت");
		button.setBounds(197, 0, 89, 23);
		getContentPane().add(button);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(335, 37, 46, 14);
		getContentPane().add(label);
		
		productElementNameField = new JTextField();
		productElementNameField.setColumns(10);
		productElementNameField.setBounds(117, 34, 208, 20);
		getContentPane().add(productElementNameField);
		
		JButton submitButton = new JButton("ثبت");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accept(new SubmitAddRemoveDialogVisitor());
			}
		});
		submitButton.setBounds(197, 438, 89, 23);
		getContentPane().add(submitButton);
		
		accept(new LoadAddRemoveDialogVisitor());
		
		setVisible(true);
		setBounds(500, 500, 700, 500);
	}
	
	public abstract void accept(AddRemoveDialogVisitor v);
	
	public void succesAndToMain(){
		JOptionPane.showMessageDialog(this, "عملیات موفق");
		this.setVisible(false);
		new MainPortalRedirectService().startRelevantMainPortal(this.userID);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
