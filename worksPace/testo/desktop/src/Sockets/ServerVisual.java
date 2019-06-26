package Sockets;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;

public class ServerVisual {

	JFrame frmElMejorServer;
	static JTextPane textPane = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerVisual window = new ServerVisual();
					window.frmElMejorServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerVisual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElMejorServer = new JFrame();
		frmElMejorServer.setTitle("El mejor server del Mundo");
		frmElMejorServer.setBounds(100, 100, 450, 300);
		frmElMejorServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmElMejorServer.getContentPane().setLayout(null);
		
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.WHITE);
		textPane.setBounds(0, 0, 6, 20);
		frmElMejorServer.getContentPane().add(textPane);
		
		JScrollPane scrollBar = new JScrollPane(textPane);
		scrollBar.setBounds(0, 0, 434, 261);
		frmElMejorServer.getContentPane().add(scrollBar);
	}
	
	public static void print(String msg) {
		textPane.setText(textPane.getText()+"\n"+msg);
	}
}
