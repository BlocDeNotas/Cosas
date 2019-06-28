package com.mygdx.game.desktop;

import java.io.IOException;
import java.net.UnknownHostException;

import Cliente.Client;
import Cliente.Lobby;
import Cliente.Login;
import Sockets.NodeJsEcho;

public class DesktopLauncher {

	static Login window = new Login();
	public static Client client = new Client();
	static Lobby l = new Lobby();
	public static Long id = null;

	public static void main(String[] arg) throws UnknownHostException, ClassNotFoundException, IOException {
		client.start();
		NodeJsEcho.main(null);
	}

	public static void print(String msg) {
		String[] comando = msg.split(" ");
		if ((Long) id != null) {
			if (comando[0].equals("/msg")) {
				System.out.println("MSGGGGG");
				l.print(comando[1]+" "+comando[2]);
			} else {
				Juego.print(msg);
				// System.out.println("Desktop: "+msg);
			}
		} else {
			System.out.println(msg);
			if (comando[0].equals("/connected")) {
				Login.frame.setVisible(false);
				l.frame.setVisible(true);

				id = Long.parseLong(comando[1]);
			} else {
				System.out.println(msg);
				System.out.println("WTFF");
			}
		}

	}
}
