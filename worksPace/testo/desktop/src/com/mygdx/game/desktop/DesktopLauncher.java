package com.mygdx.game.desktop;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import Cliente.Client;
import Cliente.Login;
import Sockets.NodeJsEcho;

public class DesktopLauncher {
	
    static Login window = new Login();
    public static Client client = new Client();
    public static Long id = null;
	public static void main(String[] arg) throws UnknownHostException, ClassNotFoundException, IOException {
		client.start();
	}

	public static void print(String msg) {
		String[] comando = msg.split(" ");
		if((Long)id != null) {
			Juego.print(msg);
		} else {
			System.out.println(msg);
			if(comando[0].equals("/connected")) {
				Login.frame.setVisible(false);
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.foregroundFPS = 60;
				new LwjglApplication(new Launcher(), config);
				id = Long.parseLong(comando[1]);
			} else {
				System.out.println(msg);
				System.out.println("WTFF");
			}
		}
		
	}
}
