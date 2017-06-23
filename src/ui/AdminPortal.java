package ui;

import javax.swing.DefaultComboBoxModel;

public class AdminPortal extends LoggedInMainPortal {

	public AdminPortal(int userID) {
		super(userID);
		
		this.pageComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
				PageTypes.CreateUser.getCaption()}));
	}

}
