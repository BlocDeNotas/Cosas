package Cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mygdx.game.desktop.DesktopLauncher;

public class Login {

	public static JFrame frame;
	private JTextField txtPepe;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
					Login.frame.setVisible(true);
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

		txtPepe = new JTextField();
		txtPepe.setText("pepe");
		txtPepe.setBounds(287, 296, 176, 20);
		frame.getContentPane().add(txtPepe);
		txtPepe.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("1234");
		textField_1.setBounds(287, 335, 176, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DesktopLauncher.client.enviar("/register " + txtPepe.getText() + " " + textField_1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(382, 366, 89, 35);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DesktopLauncher.client.enviar("/login " + txtPepe.getText() + " " + textField_1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
