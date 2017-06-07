package ui.userPanel;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	public RegisterDialog() {
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u062B\u0628\u062A \u0646\u0627\u0645");
		button.setBounds(226, 319, 89, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u0644\u063A\u0648");
		button_1.setBounds(127, 319, 89, 23);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u0628\u0627\u0632\u06AF\u0634\u062A \u0628\u0647 \u0635\u0641\u062D\u0647\u200C\u06CC \u0627\u0635\u0644\u06CC");
		button_2.setBounds(138, 11, 153, 23);
		getContentPane().add(button_2);
		
		JLabel label = new JLabel("\u0647\u0645\u0647\u200C\u06CC \u0645\u0648\u0627\u0631\u062F \u0627\u062C\u0628\u0627\u0631\u06CC \u0647\u0633\u062A\u0646\u062F");
		label.setBounds(161, 45, 142, 14);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(127, 83, 188, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 114, 188, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(127, 145, 188, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(127, 176, 188, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(127, 207, 188, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(35, 240, 280, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(127, 271, 188, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0646\u0627\u0645 \u06A9\u0627\u0631\u0628\u0631\u06CC");
		label_1.setBounds(331, 89, 62, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u06AF\u0630\u0631\u0648\u0627\u0698\u0647");
		label_2.setBounds(331, 120, 46, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u0646\u0627\u0645");
		label_3.setBounds(331, 151, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u0646\u0627\u0645 \u062E\u0627\u0646\u0648\u0627\u062F\u06AF\u06CC");
		label_4.setBounds(331, 182, 62, 14);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u0627\u06CC\u0645\u06CC\u0644");
		label_5.setBounds(331, 213, 46, 14);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u0622\u062F\u0631\u0633");
		label_6.setBounds(331, 243, 46, 14);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u0634\u0645\u0627\u0631\u0647 \u0647\u0645\u0631\u0627\u0647");
		label_7.setBounds(331, 274, 86, 14);
		getContentPane().add(label_7);
	}
}
