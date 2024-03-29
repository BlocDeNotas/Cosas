package Sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Input;

import FisicasComunes.AtaqueComun;
import FisicasComunes.PlayerComun;

public class Cliente {
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	private String ip;
	private Usuario u;

	public Usuario getUsuario() {
		return u;
	}

	public void setUsuario(Usuario u) {
		this.u = u;
	}

	public Cliente(String string) {
		ip = string;
	}

	public void procesarDatos(String s) throws IOException {
		String msgOut = "";
		if (s.length() > 0) {
			System.out.println("Ejecutando: " + s);
			if (s.charAt(0) != '/') {
				if (u == null) {
					msgOut = "Logeate para poder mandar mensajes.";
				} else if (u.getP() != null) {
					broadCast("/input " + s + " " + u.getId());
					if(Integer.parseInt(s) == Input.Keys.F) {
						System.out.println("Nuevo Ataque");
						u.getP().getAtaques().add(new AtaqueComun(u.getP().getBody(), 50, 100));
						System.out.println(u.getP().getAtaques().get(u.getP().getAtaques().size()-1).getBody().getX());
					}
					u.getP().teclasPulsadas.add(Integer.parseInt(s));
				}
			} else {
				String[] comando = s.split(" ");
				System.out.println("Ejecutando comando " + comando[0]);
				if (comando[0].equals("/login")) {
					try {
						System.out.println("ASIGNANDO USUARIO");
						u = cargarUsuario(comando[1], comando[2]);
						msgOut = "/connected " + u.getId();
					} catch (Exception e) {
						ServerVisual.print("Excepci�n al cargarUsuario.");
						e.printStackTrace();
						msgOut = "(System) Datos o formato erroneo";
					}
				} else if (comando[0].equals("/createPlayer")) {
					u.setP(new PlayerComun(0, 0));
					System.out.println("Creado Player para: " + u.getNombre());
					broadCast("/create 0 0 " + u.getId() + " " + u.getNombre());
					for (Cliente cTemp : NodeJsEcho.clientes) {
						if(!cTemp.ip.equals(this.ip)) enviarUdp(
								"/create 0 0 " + cTemp.getUsuario().getId() + " " + cTemp.getUsuario().getNombre(), ip);
					}
				} else if (comando[0].equals("/up")) {
					System.out.println(u);
					broadCast(s + " " + u.getId());
					if(u.getP().teclasPulsadas.contains((Integer) Integer.parseInt(comando[1])))u.getP().teclasPulsadas.remove((Integer) Integer.parseInt(comando[1]));
				} else if (comando[0].equals("/register")) {
					crearUsuario(comando[1], comando[2]);
				} else if (comando[0].equals("/msg")) {
					msgOut = "/msg " + this.u.getNombre() + ": " + comando[1];
					broadCast(msgOut);
				} else {
					ServerVisual.print("Comando " + comando[0] + " no encontrado.");
				}
			}
			if (!msgOut.equals("")) {
				enviarUdp(msgOut, ip);
			}
		}
	}

	private void crearUsuario(String nombre, String password) {
		consultaBases("INSERT INTO usuario VALUES ('" + nombre + "', '" + password + "')", true);
		ServerVisual.print("Usiario " + nombre + " registrado.");
	}

	public void broadCast(String s) throws UnknownHostException, IOException {
		for (Iterator<Cliente> iterator = NodeJsEcho.clientes.iterator(); iterator.hasNext();) {
			Cliente c = (Cliente) iterator.next();
			if (c != this)
				enviarUdp(/* "("+u.getNombre()+") "+ */s, c.ip);
		}
		;
	}

	public void enviarUdp(String dato, String ip) throws UnknownHostException, IOException {
		// System.out.println("ENVIANDO: "+dato+" a: "+ip.substring(1,
		// ip.length())+":"+Constantes.portCliente);
		NodeJsEcho.serverSocket.send(new DatagramPacket(dato.getBytes(), dato.length(),
				InetAddress.getByName(ip.substring(1, ip.length())), Constantes.portCliente));
	}

	public Usuario cargarUsuario(String nombre, String password) {
		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<String>> consultaBases =  (ArrayList<ArrayList<String>>) consultaBases(
				"Select * from usuario where Nombre = '" + nombre + "' and Password = '" + password + "'", false);
		Usuario u = new Usuario(consultaBases);
		return u;
	}

	public Object consultaBases(String sql, boolean b) {
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
		try {
			if (!b)
				temp = NodeJsEcho.datos.consulta(sql);
			else
				return NodeJsEcho.datos.modificacion(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
