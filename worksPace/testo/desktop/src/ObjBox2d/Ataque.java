package ObjBox2d;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;

import FisicasComunes.Body;

public class Ataque extends ObjetoFisicoPintable implements MetodosFisicos {
	private int atk; // Tipo se refiere a que guerrero es 0,1,2 o 3
	private int tDurabilidad = 30, idPlayer;
	public static HashMap<String, DatosAnimacion> animaciones = new HashMap<String, DatosAnimacion>();
	
	
	public Ataque(int atk, int tipo, int idPlayer, double x, double y) {
		this.atk = 20;// atk;
		this.body = new Body(x, y,42,18);
		this.tipo = tipo;
		this.idPlayer = idPlayer;
		this.animActual = Ataque.animaciones.get("normal").getAnimacion();
	}

	public void update() {
		this.tDurabilidad--;
		pintar(false);
		//if(this.tDurabilidad<=0) Player.buscarPlayer(this.idPlayer).getAtaques().remove(this);
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

}
