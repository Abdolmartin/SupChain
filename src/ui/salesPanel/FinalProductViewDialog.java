package ui.salesPanel;

import ui.GeneralProductElementViewDialog;
import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;
import ui.handler.SalesAndSupplyFacade;
import ui.userPanel.ViewActionHistoryDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import common.Constants;
import common.JsonToJTableService;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class FinalProductViewDialog extends GeneralProductElementViewDialog {
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTextArea descArea;
	
	public FinalProductViewDialog(int userID, int productID) {
		super(userID, productID);
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(410, 79, 46, 14);
		getContentPane().add(label);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(102, 76, 298, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setBounds(102, 107, 298, 20);
		getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0642\u06CC\u0645\u062A");
		label_1.setBounds(410, 110, 46, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u062A\u0648\u0635\u06CC\u0641");
		label_2.setBounds(410, 135, 46, 14);
		getContentPane().add(label_2);
		
		descArea = new JTextArea();
		descArea.setEditable(false);
		descArea.setBounds(102, 138, 298, 134);
		getContentPane().add(descArea);
		
		JButton orderButton = new JButton("\u0633\u0641\u0627\u0631\u0634");
		orderButton.setBounds(102, 373, 89, 23);
		getContentPane().add(orderButton);
		
		quantityField = new JTextField();
		quantityField.setText("\u06F1");
		quantityField.setBounds(201, 374, 33, 20);
		getContentPane().add(quantityField);
		quantityField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u062A\u0639\u062F\u0627\u062F");
		label_3.setBounds(241, 377, 46, 14);
		getContentPane().add(label_3);
		
		JButton reviewButton = new JButton("ثبت بازخورد");
		reviewButton.setBounds(102, 407, 89, 23);
		getContentPane().add(reviewButton);
		
		JLabel label_4 = new JLabel("تنها در صورتی که قبلا خریداری کرده‌اید");
		label_4.setBounds(201, 411, 179, 14);
		getContentPane().add(label_4);
		
		JButton viewReviewsButton = new JButton("مشاهده بازخوردها");
		viewReviewsButton.setBounds(88, 340, 118, 23);
		getContentPane().add(viewReviewsButton);
		
		load();
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
	}
	
	void load(){
		SalesAndSupplyFacade salesAndSupplyFacade = new SalesAndSupplyFacade();
		JSONObject productInfo = salesAndSupplyFacade.getProductElementInfo(productElementID, Constants.PRODUCT);
		if (productInfo.containsKey("error")){
			JOptionPane.showMessageDialog(this, "یافت نشد! به صفحه‌ی اصلی منتقل می‌شوید.");
			new MainPortalRedirectService().startRelevantMainPortal(this.userID);
		} else{
			this.nameField.setText((String)productInfo.get("name"));
			this.priceField.setText((String)productInfo.get("price"));
			this.descArea.setText((String)productInfo.get("desc"));
		}
	}
}
