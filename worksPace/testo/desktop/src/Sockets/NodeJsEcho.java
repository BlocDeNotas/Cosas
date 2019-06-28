package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import FisicasComunes.Body;
import FisicasComunes.Fisics;

import java.sql.*;


public class NodeJsEcho extends Thread {
	
	// socket object
	public static DatagramSocket serverSocket = null;
	static BaseDeDatos datos;
	DatagramPacket dato;
	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		NodeJsEcho m = new NodeJsEcho();
        m.dato = new DatagramPacket(new byte[100], 100);
		m.serverSocket = new DatagramSocket(Constantes.portServer);
		m.start();
		ServerVisual s = new ServerVisual();
		s.frmElMejorServer.setVisible(true);
		s.print("Puerto: "+m.serverSocket.getLocalPort());
		
	}
	
	public NodeJsEcho() {
		// TODO Auto-generated constructor stub
		ServerVisual.print("Iniciando Base de datos");
		datos = new BaseDeDatos();
		ServerVisual.print("Base de datos iniciada");
	}

	
	@Override
	public void run() {
		try {
			new Timer().scheduleAtFixedRate(new TimerTask(){
			    @Override
			    public void run(){
			      for (Cliente c : clientes) {
					if(c.getUsuario()!= null) {
						for (Cliente c2 : clientes) {
							if(c2.getUsuario()!=null && c2.getUsuario().getP()!= null) {
								Body pBody = c2.getUsuario().getP().getBody();
								try {
									c.broadCast("/syncro "+pBody.getX()+" "+pBody.getY()+" "+c2.getUsuario().getId()+" "+pBody.getVelx()+" "+pBody.getVely());
									c.enviarUdp(("/syncro "+pBody.getX()+" "+pBody.getY()+" "+c2.getUsuario().getId()+" "+pBody.getVelx()+" "+pBody.getVely()),c.getIp());
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
			      }
			    }
			},0,50);
			new Timer().scheduleAtFixedRate(new TimerTask(){
			    @Override
			    public void run(){
			    	Fisics.update();
			    }
			},0,(long)(1000/60));
			
			while(true) {
				Cliente c = null;
				serverSocket.receive(dato);
				if(dato!=null) {
					for (Cliente cliente : clientes) {
						if(cliente.getIp().equals(""+dato.getAddress())) {
							c = cliente;
						}
						else {
							System.out.println("IP: "+cliente.getIp()+" Encontrada");
						}
						
					}
					DatoUdp datoRecibido = DatoUdp.fromByteArray(dato.getData());
					if(c != null) {
						c.procesarDatos(datoRecibido.toString());
					} else {
						ServerVisual.print("Se ha conectado un cliente");
						Cliente cTemp = new Cliente(""+dato.getAddress());
						clientes.add(cTemp);
						cTemp.procesarDatos(datoRecibido.toString());
					}
				}
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}