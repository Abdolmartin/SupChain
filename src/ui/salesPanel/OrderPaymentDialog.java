package ui.salesPanel;

import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.handler.OrganisationManagementFacade;

import javax.swing.JTextField;

import common.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class OrderPaymentDialog extends LoggedInWindow {
	int orderID;
	private JTextField orderIDField;
	private JTextField paymentCodeField;
	public OrderPaymentDialog(int userID, int orderID) {
		super(userID);
		this.orderID = orderID;
		
		orderIDField = new JTextField();
		orderIDField.setEditable(false);
		orderIDField.setBounds(107, 102, 227, 20);
		getContentPane().add(orderIDField);
		orderIDField.setColumns(10);
		
		JLabel label = new JLabel("\u0634\u0645\u0627\u0631\u0647 \u0633\u0641\u0627\u0631\u0634");
		label.setBounds(344, 105, 81, 14);
		getContentPane().add(label);
		
		paymentCodeField = new JTextField();
		paymentCodeField.setBounds(107, 134, 227, 20);
		getContentPane().add(paymentCodeField);
		paymentCodeField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u06A9\u062F \u067E\u0631\u062F\u0627\u062E\u062A");
		label_1.setBounds(344, 137, 70, 14);
		getContentPane().add(label_1);
		
		JButton submitButton = new JButton("\u062B\u0628\u062A \u067E\u0631\u062F\u0627\u062E\u062A");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submit();
			}
		});
		submitButton.setBounds(183, 213, 89, 23);
		getContentPane().add(submitButton);
		
		JButton cancelButton = new JButton("\u0644\u063A\u0648 \u0633\u0641\u0627\u0631\u0634");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submitCancel();
			}
		});
		cancelButton.setBounds(183, 0, 89, 23);
		getContentPane().add(cancelButton);
		load();
		this.setVisible(true);
		this.setBounds(500, 500, 500, 500);
	}
	void load(){
		this.orderIDField.setText(String.valueOf(this.orderID));
	}
	
	void submit(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		String result = organisationManagementFacade.addOrderPayment(this.orderID, this.paymentCodeField.getText());
		if (result.equals(Constants.SUCCESS)){
			JOptionPane.showMessageDialog(this, "عملیات موفق");
			this.setVisible(false);
			new MainPortalRedirectService().startRelevantMainPortal(userID);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else {
			JOptionPane.showMessageDialog(this, "ناموفق، دوباره تلاش کنید");
			return;
		}
	}
	
	void submitCancel(){
		OrganisationManagementFacade organisationManagementFacade = new OrganisationManagementFacade();
		organisationManagementFacade.cancelOrder(orderID);
		JOptionPane.showMessageDialog(this, "با موفقیت لغو شد");
		this.setVisible(false);
		new MainPortalRedirectService().startRelevantMainPortal(userID);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
