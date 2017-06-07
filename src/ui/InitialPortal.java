package ui;

import javax.swing.JDialog;

import ui.userPanel.LoginDialog;
import ui.userPanel.RegisterDialog;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class InitialPortal extends JDialog {
	public InitialPortal() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u0648\u0631\u0648\u062F \u0628\u0647 \u0633\u0627\u0645\u0627\u0646\u0647");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InitialPortal.this.setVisible(false);
				JDialog loginDialog = new LoginDialog();
				InitialPortal.this.dispatchEvent(new WindowEvent(InitialPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		button.setBounds(155, 106, 127, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u062B\u0628\u062A \u0646\u0627\u0645");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InitialPortal.this.setVisible(false);
				JDialog registerDialog = new RegisterDialog();
				InitialPortal.this.dispatchEvent(new WindowEvent(InitialPortal.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		button_1.setBounds(155, 140, 127, 23);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u062C\u0633\u062A\u062C\u0648\u06CC \u0645\u062D\u0635\u0648\u0644\u0627\u062A");
		button_2.setBounds(155, 174, 127, 23);
		getContentPane().add(button_2);
		
		setBounds(500, 500, 456, 414);
		setVisible(true);
	}

}
