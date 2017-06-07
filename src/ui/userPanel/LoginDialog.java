package ui.userPanel;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class LoginDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	public LoginDialog() {
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u0648\u0631\u0648\u062F \u0628\u0647 \u0633\u0627\u0645\u0627\u0646\u0647");
		button.setBounds(170, 278, 112, 23);
		getContentPane().add(button);
		
		textField = new JTextField();
		textField.setBounds(139, 146, 160, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 177, 160, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC");
		label.setBounds(309, 149, 46, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647");
		label_1.setBounds(309, 180, 46, 14);
		getContentPane().add(label_1);
		
		JButton button_1 = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647\u200C\u06CC \u0627\u0635\u0644\u06CC");
		button_1.setBounds(146, 11, 153, 23);
		getContentPane().add(button_1);
	}
	
	void submit(){
		
	}
	
}
