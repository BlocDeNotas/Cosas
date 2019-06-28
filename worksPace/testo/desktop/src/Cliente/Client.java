package Cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.mygdx.game.desktop.DesktopLauncher;

import Sockets.Constantes;
import Sockets.DatoUdp;

public class Client extends Thread {
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
			socket = new DatagramSocket(Constantes.portCliente);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enviar(String accion) throws IOException {
		DatoUdp elDato = new DatoUdp(accion);
		byte[] elDatoEnBytes = elDato.toByteArray();
		dato = new DatagramPacket(elDatoEnBytes, elDatoEnBytes.length, InetAddress.getByName(Constantes.ipServer),
				Constantes.portServer);
		socket.send(dato);
		// System.out.println(pw.checkError());
		// System.out.println("accion enviada al server: "+accion);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String s = new String(packet.getData(), 0, packet.getLength());

				if (s != null) {
					DesktopLauncher.print(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
