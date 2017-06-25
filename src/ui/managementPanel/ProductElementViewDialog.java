package ui.managementPanel;

import ui.GeneralProductElementViewDialog;
import ui.LoggedInWindow;
import ui.MainPortalRedirectService;
import ui.ReturnToMainButton;
import ui.handler.OrganisationManagementFacade;
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

public class ProductElementViewDialog extends GeneralProductElementViewDialog {
	private JTextField nameField;
	private JTextField priceField;
	private JTextArea descArea;
	private String productElementType;
	private JTextField lowerBoundField;
	private JTextField upperBoundField;
	private JTextField inventoryField;
	private JTextField typeField;
	private JTextField idField;
	private JLabel finalProductLabel;
	
	public ProductElementViewDialog(int userID, int productElementID, String productElementType) {
		super(userID, productElementID);
		this.productElementType = productElementType;
		
		JLabel label = new JLabel("\u0646\u0627\u0645");
		label.setBounds(398, 78, 46, 14);
		getContentPane().add(label);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(90, 75, 298, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setBounds(90, 137, 298, 20);
		getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0642\u06CC\u0645\u062A");
		label_1.setBounds(398, 134, 46, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u062A\u0648\u0635\u06CC\u0641");
		label_2.setBounds(398, 173, 46, 14);
		getContentPane().add(label_2);
		
		descArea = new JTextArea();
		descArea.setEditable(false);
		descArea.setBounds(90, 168, 298, 134);
		getContentPane().add(descArea);
		
		
		lowerBoundField = new JTextField();
		lowerBoundField.setBounds(90, 313, 298, 20);
		getContentPane().add(lowerBoundField);
		lowerBoundField.setColumns(10);
		
		JLabel label_3 = new JLabel("حد پایین");
		label_3.setBounds(398, 316, 46, 14);
		getContentPane().add(label_3);
		
		JLabel lblNewLabel = new JLabel("حد بالا");
		lblNewLabel.setBounds(398, 341, 46, 14);
		getContentPane().add(lblNewLabel);
		
		upperBoundField = new JTextField();
		upperBoundField.setBounds(90, 338, 298, 20);
		getContentPane().add(upperBoundField);
		upperBoundField.setColumns(10);
		
		JLabel label_4 = new JLabel("موجودی");
		label_4.setBounds(398, 366, 46, 14);
		getContentPane().add(label_4);
		
		inventoryField = new JTextField();
		inventoryField.setEditable(false);
		inventoryField.setBounds(90, 363, 298, 20);
		getContentPane().add(inventoryField);
		inventoryField.setColumns(10);
		
		finalProductLabel = new JLabel("محصول نهایی");
		finalProductLabel.setBounds(13, 109, 67, 14);
		getContentPane().add(finalProductLabel);
		
		typeField = new JTextField();
		typeField.setEditable(false);
		typeField.setBounds(90, 106, 298, 20);
		getContentPane().add(typeField);
		typeField.setColumns(10);
		
		JLabel label_5 = new JLabel("نوع");
		label_5.setBounds(398, 110, 46, 14);
		getContentPane().add(label_5);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(398, 53, 46, 14);
		getContentPane().add(lblId);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(89, 50, 299, 20);
		getContentPane().add(idField);
		idField.setColumns(10);
		
		JButton changeInvBoundsButton = new JButton("تغییر حدود موجودی");
		changeInvBoundsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				submitInvBounds();
			}
		});
		changeInvBoundsButton.setBounds(75, 425, 121, 23);
		getContentPane().add(changeInvBoundsButton);
		
		JLabel label_6 = new JLabel("در صورت عدم تمای به تغییر، فیلد را خالی بگذارید");
		label_6.setBounds(206, 429, 238, 14);
		getContentPane().add(label_6);
		
		JButton changeInvButton = new JButton("تغییر موجودی");
		changeInvButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToChangeInv();
			}
		});
		changeInvButton.setBounds(75, 456, 121, 23);
		getContentPane().add(changeInvButton);
		
		load();
		
		this.setBounds(500, 500, 516, 573);
		this.setVisible(true);
	}
	
	protected void submitInvBounds() {
		int low, high;
		if (this.lowerBoundField.getText().equals(""))
			low = -1;
		else{
			try{
				low = Integer.valueOf(this.lowerBoundField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		
		if (this.upperBoundField.getText().equals(""))
			high = -1;
		else{
			try{
				high = Integer.valueOf(this.upperBoundField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		OrganisationManagementFacade orgManagementFacade = new OrganisationManagementFacade();
		String result = orgManagementFacade.setInventoryBounds(productElementID, low, high);
		if (result.equals(Constants.SUCCESS)){
			this.setVisible(false);
			new ProductElementViewDialog(userID, productElementID, productElementType);
			this.dispatchEvent(new WindowEvent(ProductElementViewDialog.this, WindowEvent.WINDOW_CLOSING));
		}
		else if (result.equals(Constants.INVALID_INFO)){
			JOptionPane.showMessageDialog(this, "اطلاعات نادرست است.");
		}
		else {
			JOptionPane.showMessageDialog(this, "خطایی رخ داده، به صفحه‌ی اصلی برگردانده می‌شوید.");
			this.setVisible(false);
			new MainPortalRedirectService().startRelevantMainPortal(userID);
			this.dispatchEvent(new WindowEvent(ProductElementViewDialog.this, WindowEvent.WINDOW_CLOSING));
		}
	}

	void load(){
		OrganisationManagementFacade orgManagementFacade = new OrganisationManagementFacade();
		JSONObject productElementInfo = orgManagementFacade.getProductElementInfo(productElementID, Constants.PRODUCT);
		if (productElementInfo.containsKey("error")){
			JOptionPane.showMessageDialog(this, "یافت نشد! به صفحه‌ی اصلی منتقل می‌شوید.");
			new MainPortalRedirectService().startRelevantMainPortal(this.userID);
		} else{
			this.nameField.setText((String)productElementInfo.get("name"));
			this.priceField.setText((String)productElementInfo.get("price"));
			this.descArea.setText((String)productElementInfo.get("desc"));
			this.inventoryField.setText((String)productElementInfo.get("inventory"));
			this.lowerBoundField.setText((String)productElementInfo.get("lower"));
			this.upperBoundField.setText((String)productElementInfo.get("upper"));
			this.typeField.setText((String)productElementInfo.get("type"));
			this.idField.setText((String)productElementInfo.get("id"));
			if (productElementInfo.containsKey("final?")){
				if ("true".equals((String)productElementInfo.get("final?")))
					this.finalProductLabel.setVisible(true);
				else
					this.finalProductLabel.setVisible(false);
			}
			else{
				this.finalProductLabel.setVisible(false);
			}
		}
	}
	
	void goToChangeInv(){
		this.setVisible(false);
		new AddRemoveItemDialog(userID, productElementID);
		this.dispatchEvent(new WindowEvent(ProductElementViewDialog.this, WindowEvent.WINDOW_CLOSING));
	}
}
