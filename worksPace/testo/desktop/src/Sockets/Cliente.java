package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import FisicasComunes.PlayerComun;

public class Cliente{
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
		// TODO Auto-generated method stub
		byte[] buf = s.getBytes();
		String msgOut = "";
		if(s.length()>0) {
			System.out.println("Ejecutando: "+s);
			if(s.charAt(0)!='/') {
				 if(u== null) {
					 msgOut = "Logeate para poder mandar mensajes.";
				} else {
					broadCast("/input "+s+" "+u.getId());
					u.getP().teclasPulsadas.add(Integer.parseInt(s));
				}
			}else {
				String[] comando = s.split(" ");
				System.out.println("Ejecutando comando "+comando[0]);
				if(comando[0].equals("/login")) {
					try {
						System.out.println("ASIGNANDO USUARIO");
						u = cargarUsuario(comando[1],comando[2]);
						msgOut = "/connected "+u.getId();
						broadCast("/create 0 0 "+u.getId()+" "+u.getNombre());
						for (Cliente cTemp : NodeJsEcho.clientes) {
							u.setP(new PlayerComun(0,0));
							enviarUdp("/create 0 0 "+cTemp.getUsuario().getId()+" "+cTemp.getUsuario().getNombre());
						}
					} catch (Exception e) {
						ServerVisual.print("Excepción al cargarUsuario.");
						e.printStackTrace();
						msgOut = "(System) Datos o formato erroneo";
						
					}
				}else if(comando[0].equals("/up")){
					System.out.println(u);
					broadCast(s+" "+u.getId());
					u.getP().teclasPulsadas.remove((Integer)Integer.parseInt(comando[1]));
				}else if(comando[0].equals("/register")){
					crearUsuario(comando[1],comando[2]);
				} else {
					ServerVisual.print("Comando "+comando[0]+" no encontrado.");
				}
			}
			if(!msgOut.equals("")) {
				enviarUdp(msgOut);
			}
		} 
	}

	private void crearUsuario(String nombre, String password) {
		consultaBases("INSERT INTO usuario VALUES ('"+nombre+"', '"+password+"')",true);
		ServerVisual.print("Usiario "+nombre+" registrado.");
	}

	public void broadCast(String s) throws UnknownHostException, IOException {
		for (Iterator iterator = NodeJsEcho.clientes.iterator(); iterator.hasNext();) {
			Cliente c = (Cliente) iterator.next();
			/*if(c!=this)*/enviarUdp(/*"("+u.getNombre()+") "+*/s);
		};
	}
	
	public void enviarUdp(String dato) throws UnknownHostException, IOException {
		NodeJsEcho.serverSocket.send(new DatagramPacket(dato.getBytes(), dato.length(), InetAddress.getByName("localhost"),Constantes.portCliente));
	}
	
	public Usuario cargarUsuario(String nombre, String password) {
		Usuario u = new Usuario((ArrayList<ArrayList<String>>) consultaBases("Select * from usuario where Nombre = '"+nombre+"' and Password = '"+password+"'",false));
		return u;
	}
	
	public Object consultaBases(String sql, boolean b) {
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
		try {
			if(!b)temp = NodeJsEcho.datos.consulta(sql);
			else return NodeJsEcho.datos.modificacion(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
