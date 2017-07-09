package ui;

import javax.swing.DefaultComboBoxModel;

public class EmployeePortal extends LoggedInMainPortal {

	public EmployeePortal(int userID) {
		super(userID);
		this.pageComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
				PageTypes.ProductElementSearch.getCaption()}));
	}
}
