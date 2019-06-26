package Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mygdx.game.desktop.DesktopLauncher;
import com.mygdx.game.desktop.Juego;

import Sockets.DatoUdp;
import Sockets.NodeJsEcho;
public class Client extends Thread{
	private DatagramSocket socket;
	DatagramPacket dato;
	private byte[] buf = new byte[256];
    public static void main(String[] args) {
		new Client();
	}
    
    public Client() {
    	iniciar();
    }
    
    public void iniciar() {
		try {
			socket = new DatagramSocket(55286, InetAddress
			        .getByName("localhost"));
			if(!socket.isConnected()) {
				System.out.println("Servidor no encontrado, SADASDJOSADJASD");
				try {
					NodeJsEcho.main(null);
					iniciar();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
    }
    
    public void enviar(String accion) throws IOException {
    	DatoUdp elDato = new DatoUdp(accion);
        byte[] elDatoEnBytes = elDato.toByteArray();
        dato = new DatagramPacket(elDatoEnBytes,
                elDatoEnBytes.length, InetAddress
                        .getByName("localhost"),
                55286);
    	socket.send(dato);
    	//System.out.println(pw.checkError());
        System.out.println("accion enviada al server: "+accion);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				DatagramPacket packet 
	              = new DatagramPacket(buf, buf.length);
	            socket.receive(packet);
				String s = new String(packet.getData(), 0, packet.getLength());
				
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
