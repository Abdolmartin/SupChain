package ui;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class CustomerPortal extends LoggedInMainPortal {

	public CustomerPortal(int userID) {
		super(userID);
		
		this.pageComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
				PageTypes.FinalProductSearch.getCaption()}));
	}

}
