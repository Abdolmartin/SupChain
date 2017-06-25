package ui.managementPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class OtherAddProductDialog extends AbstractAddOrRemoveDialog{
	private JSpinner countSpinner;
	private JTextField priceField;
	
	public JSpinner getCountSpinner() {
		return countSpinner;
	}
	
	public JTextField getPriceField(){
		return this.priceField;
	}

	public OtherAddProductDialog(int userID, int productElementID) {
		super(userID, productElementID);
		
		countSpinner = new JSpinner();
		countSpinner.setBounds(210, 346, 29, 20);
		getContentPane().add(countSpinner);
		
		JLabel label = new JLabel("\u062A\u0639\u062F\u0627\u062F");
		label.setBounds(249, 349, 46, 14);
		getContentPane().add(label);
		
		priceField = new JTextField();
		priceField.setBounds(153, 315, 86, 20);
		getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0642\u06CC\u0645\u062A");
		label_1.setBounds(249, 318, 46, 14);
		getContentPane().add(label_1);
		accept(new LoadAddRemoveDialogVisitor());
	}

	@Override
	public void accept(AddRemoveDialogVisitor v) {
		v.visitOtherAddProductDialog(this);
	}
}
