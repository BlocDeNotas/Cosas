package Sockets;

import java.util.ArrayList;

import ObjBox2d.Body;

public class Player {
	public ArrayList<Integer> teclasPulsadas = new ArrayList<Integer>();
	private Body body;
	private boolean dir;
	public ArrayList<Integer> getTeclasPulsadas() {
		return teclasPulsadas;
	}
	public void setTeclasPulsadas(ArrayList<Integer> teclasPulsadas) {
		this.teclasPulsadas = teclasPulsadas;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public boolean isDir() {
		return dir;
	}
	public void setDir(boolean dir) {
		this.dir = dir;
	}
	
	public Player(double x, double y) {
		this.body = new Body(x, y, 0, 0); //TODO
		this.dir = true;
	}
	
}
