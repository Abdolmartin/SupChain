package ui;

import javax.swing.JButton;

public class GeneralProductElementViewDialog extends LoggedInWindow {
	
	protected int productElementID;
	
	public GeneralProductElementViewDialog(int userID, int productElementID) {
		super(userID);
		this.productElementID = productElementID;
		
		JButton returnToMainButton = new ReturnToMainButton(this.userID, this, "بازگشت به صفحه‌ی اصلی");
		returnToMainButton.setBounds(166, 0, 173, 23);
		getContentPane().add(returnToMainButton);
	}
	
	
}
