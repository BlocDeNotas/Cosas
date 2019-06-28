package Cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.desktop.DesktopLauncher;
import com.mygdx.game.desktop.Launcher;

public class Lobby {

	public JFrame frame;
	private JTextField textField;
	JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby window = new Lobby();
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
	public Lobby() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.foregroundFPS = 60;
				new LwjglApplication(new Launcher(), config);

			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\Covacs\\Desktop\\ascensor\\ascensor\\assets\\img\\play.png"));
		button.setBounds(49, 199, 345, 51);
		frame.getContentPane().add(button);

		JLabel lblGold = new JLabel("");
		lblGold.setIcon(new ImageIcon(
				"C:\\Users\\Covacs\\Desktop\\Biorush\\android\\build\\intermediates\\assets\\debug\\Kickpixel's - RPG Icons\\coin_gold.png"));
		lblGold.setBounds(329, 9, 95, 32);
		frame.getContentPane().add(lblGold);

		textField = new JTextField();
		textField.setBounds(49, 168, 234, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.BLACK);
		textPane.setBounds(49, 52, 234, 116);
		frame.getContentPane().add(textPane);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(49, 52, 234, 116);
		frame.getContentPane().add(scrollPane);

		JLabel lblLobby = new JLabel("Lobby");
		lblLobby.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLobby.setBounds(141, 11, 86, 20);
		frame.getContentPane().add(lblLobby);

		JList<String> list = new JList<String>();
		list.setBackground(Color.DARK_GRAY);
		list.setBounds(285, 52, 94, 107);
		frame.getContentPane().add(list);

		JLabel lblChat = new JLabel("Chat");
		lblChat.setBounds(50, 29, 46, 14);
		frame.getContentPane().add(lblChat);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setBounds(285, 29, 46, 14);
		frame.getContentPane().add(lblUsers);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		btnNewButton.setBounds(285, 165, 94, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Covacs\\Desktop\\5b4f13d5da341_thumb900.jpg"));
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(label);
	}

	public void print(String msg) {
		this.textPane.setText(textPane.getText() + "\n" + msg);
	}

	public void send() {
		try {
			DesktopLauncher.client.enviar("/msg " + textField.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
