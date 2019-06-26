package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Cliente extends Thread {
	private Socket socket;
	PrintWriter out;
	private BufferedReader in;
	private Usuario u;
	
	public Usuario getUsuario() {
		return u;
	}

	public void setUsuario(Usuario u) {
		this.u = u;
	}

	public Cliente(Socket s) {
		socket = s;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			while(true) {
				String s = in.readLine();
				if(s.length()>0) {
					if(s.charAt(0)!='/') {
						 if(u== null) {
							out.println("Logeate para poder mandar mensajes.");
						} else {
							broadCast("/input "+s+" "+u.getId());
							u.getP().teclasPulsadas.add(Integer.parseInt(s));
						}
					}else {
						String[] comando = s.split(" ");
						System.out.println("Ejecutando comando "+comando[0]);
						if(comando[0].equals("/login")) {
							try {
								u = cargarUsuario(comando[1],comando[2]);
								out.println("/connected "+u.getId());
								broadCast("/create 0 0 "+u.getId()+" "+u.getNombre());
								for (Cliente cTemp : NodeJsEcho.clientes) {
									u.setP(new Player(0,0));
									out.println("/create 0 0 "+cTemp.getUsuario().getId()+" "+cTemp.getUsuario().getNombre());
								}
							} catch (Exception e) {
								ServerVisual.print("Excepción al cargarUsuario.");
								e.printStackTrace();
								out.println("(System) Datos o formato erroneo");
								
							}
						}else if(comando[0].equals("/up")){
							broadCast(s+" "+u.getId());
							u.getP().teclasPulsadas.remove((Integer)Integer.parseInt(comando[1]));
						}else {
							ServerVisual.print("Comando "+comando[0]+" no encontrado.");
						}
					}
				} 
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				broadCast("(System) Ha ocurrido un fallo crítico en el servidor.");
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void broadCast(String s) {
		for (Iterator iterator = NodeJsEcho.clientes.iterator(); iterator.hasNext();) {
			Cliente c = (Cliente) iterator.next();
			if(c!=this)c.out.println(/*"("+u.getNombre()+") "+*/s);
		};
	}
	
	public Usuario cargarUsuario(String nombre, String password) {
		Usuario u = new Usuario(consultaBases("Select * from usuario where Nombre = '"+nombre+"' and Password = '"+password+"'"));
		return u;
	}
	
	public ArrayList<ArrayList<String>> consultaBases(String sql) {
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
		try {
			temp = NodeJsEcho.datos.consulta(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
