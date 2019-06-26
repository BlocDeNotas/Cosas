package FisicasComunes;

public class AtaqueComun {
	private Body body;
	private int atk, hp;
	public AtaqueComun(Body body, int atk, int hp) {
		super();
		this.body = body;
		this.atk = atk;
		this.hp = hp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
}
