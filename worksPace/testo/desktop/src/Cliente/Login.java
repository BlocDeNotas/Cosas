package Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import com.mygdx.game.desktop.DesktopLauncher;
import com.mygdx.game.desktop.Juego;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(287, 296, 176, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(287, 335, 176, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DesktopLauncher.client.enviar("/register "+textField.getText()+" "+textField_1.getText());
			}
		});
		btnNewButton.setBounds(382, 366, 89, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DesktopLauncher.client.enviar("/login "+textField.getText()+" "+textField_1.getText());
			}
		});
		btnNewButton_1.setBounds(231, 421, 197, 47);
		frame.getContentPane().add(btnNewButton_1);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(184, 372, 97, 23);
		frame.getContentPane().add(checkBox);
		
		checkBox.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Covacs\\Desktop\\login_screen-950x750.jpg"));
		lblNewLabel.setBounds(0, 0, 693, 666);
		frame.getContentPane().add(lblNewLabel);
		
		frame.setVisible(true);
	}
}
