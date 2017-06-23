package ui;

import javax.swing.DefaultComboBoxModel;

public class EmployeePanel extends LoggedInMainPortal {

	public EmployeePanel(int userID) {
		super(userID);
		this.pageComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
				PageTypes.ProductElementSearch.getCaption()}));
	}
}
