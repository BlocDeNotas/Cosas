package Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mygdx.game.desktop.DesktopLauncher;
import com.mygdx.game.desktop.Juego;

import Sockets.NodeJsEcho;
public class Client extends Thread{
	private Socket socket;
    private PrintWriter pw;
    private InputStreamReader isr;
    private BufferedReader br;
    
    public static void main(String[] args) {
		new Client();
	}
    
    public Client() {
    	iniciar();
    }
    
    public void iniciar() {
    	try {
			socket = new Socket("127.0.0.1",55286);
			 pw = new PrintWriter(socket.getOutputStream(), true);
	        isr = new InputStreamReader(socket.getInputStream());
	        br = new BufferedReader(isr);
		} catch (ConnectException e) {
			System.out.println("Servidor no encontrado, iniciando server local");
			try {
				NodeJsEcho.main(null);
				iniciar();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public void enviar(String accion) {
    	pw.println(accion);
    	//System.out.println(pw.checkError());
        System.out.println("accion enviada al server: "+accion);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				String s = br.readLine();
				
				if(s != null) {
					DesktopLauncher.print(s);
					//System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
