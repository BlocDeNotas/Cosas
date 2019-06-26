package FisicasComunes;

import java.util.ArrayList;

public class PlayerComun {
	public ArrayList<Integer> teclasPulsadas = new ArrayList<Integer>();
	private Body body;
	private boolean dir;
	private int hp;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}

	private ArrayList<AtaqueComun> ataques = new ArrayList<AtaqueComun>();
	public ArrayList<AtaqueComun> getAtaques() {
		return ataques;
	}
	public void setAtaques(ArrayList<AtaqueComun> ataques) {
		this.ataques = ataques;
	}
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
	
	public PlayerComun(double x, double y) {
		this.body = new Body(x, y, 0, 0); //TODO
		this.ataques.add(new AtaqueComun(this.body, 10, 20));
		this.dir = true;
	}
	
}
