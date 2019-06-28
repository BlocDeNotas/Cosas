package ObjBox2d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.mygdx.game.desktop.DesktopLauncher;
import com.mygdx.game.desktop.Juego;

import FisicasComunes.Body;

public class Player extends ObjetoFisicoPintable implements MetodosFisicos {
	private int hp;
	private int comboEspadaTimer = 0, contCombo = 0;
	public static final int comboEspadaMaxTime = 15, rowSpritesheet = 7, heightSpritesheet = 16,
			saltarColumnaSpritesheet = 8;
	private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
	private long id;
	public static HashMap<String, DatosAnimacion> animaciones = new HashMap<String, DatosAnimacion>();
	public ArrayList<Integer> teclasPulsadas = new ArrayList<Integer>();

	public int getContCombo() {
		return contCombo;
	}

	public void setContCombo(int contCombo) {
		this.contCombo = contCombo;
	}

	public Player(int hp, int tipo, int id) {

		// this.animActual = Player.animacionIdle;
		this.hp = 20;// atk;
		this.id = id;
		this.body = new Body(120, 40, 52, 39);
		/* =========================================== */
		this.body.setX(100);
		this.body.setY(50);
		this.tipo = tipo;
		this.setAnimActual(Player.animaciones.get("idle").getAnimacion());

	}

	public void update() {

		this.body.update();
		for (Ataque ataque : this.ataques) {
			ataque.update();
		}
		try {
			this.updateAnimacion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pintar(false);

	}

	public void updateAnimacion() throws IOException {
		Player p = this;
		ArrayList<Integer> teclasPulsadas = p.teclasPulsadas;
		Body pBody = p.getBody();
		boolean loop = true;
		for (Integer input : Juego.controlesSinLoop) {
			for (Integer input2 : teclasPulsadas) {
				if (input == input2)
					loop = false;
			}
		}
		p.setAnimLoop(loop);
		if (p.getBody().getVely() > 0) {
			// if(p.getBody().getY()<Juego.ySuelo+100)
			// p.setAnimActual(Player.animaciones.get("iniciarSalto").getAnimacion());
			p.setAnimActual(Player.animaciones.get("rodarAire").getAnimacion());
		} else if (p.getBody().getVely() < 0)
			p.setAnimActual(Player.animaciones.get("caer").getAnimacion());
		else if (!loop) {
			if (teclasPulsadas.contains(Input.Keys.A)) {
				teclasPulsadas.remove((Integer) Input.Keys.A);
			}
			if (teclasPulsadas.contains(Input.Keys.D)) {
				teclasPulsadas.remove((Integer) Input.Keys.D);
			}
			if (teclasPulsadas.contains(Input.Keys.F)) {
				p.getAnimActual().setFrameDuration((float) 0.05);
				p.setComboEspadaTimer(Player.comboEspadaMaxTime);
				switch (p.getContCombo()) {
				case 0:
					if (p.getAnimActual() != Player.animaciones.get("ataque1").getAnimacion()) {
						// p.getAtaques().add(new Ataque(10, 0, 0,p.getBody().getX(),150));
						p.setTimerAnim(0);
					}
					p.setAnimActual(Player.animaciones.get("ataque1").getAnimacion());
					break;
				case 1:
					if (p.getAnimActual() != Player.animaciones.get("ataque2").getAnimacion()) {
						// p.getAtaques().add(new Ataque(10, 0, 0,p.getBody().getX(),50));
						p.setTimerAnim(0);
					}
					p.setAnimActual(Player.animaciones.get("ataque2").getAnimacion());
					break;
				case 2:
					if (p.getAnimActual() != Player.animaciones.get("ataque3").getAnimacion()) {
						p.setTimerAnim(0);
						// p.getAtaques().add(new Ataque(10, 0, 0,p.getBody().getX(),50));
					}
					p.setAnimActual(Player.animaciones.get("ataque3").getAnimacion());
					break;
				}
				if (p.getAnimActual().isAnimationFinished(p.getTimerAnim())) {
					if (p.getContCombo() == 0) {
						p.setContCombo(1);
						teclasPulsadas.remove((Integer) Input.Keys.F);
						DesktopLauncher.client.enviar("/up " + (Integer) Input.Keys.F);
					} else if (p.getContCombo() == 1) {
						p.setContCombo(2);
						teclasPulsadas.remove((Integer) Input.Keys.F);
						DesktopLauncher.client.enviar("/up " + (Integer) Input.Keys.F);
					} else if (p.getContCombo() == 2) {
						p.setComboEspadaTimer(0);
						p.setContCombo(0);
						teclasPulsadas.remove((Integer) Input.Keys.F);
						DesktopLauncher.client.enviar("/up " + (Integer) Input.Keys.F);
					}
					p.setTimerAnim(0);
				} else {

				}
			} else if (teclasPulsadas.contains(Input.Keys.SPACE)) {
				if (pBody.getVelx() != 0) {
					p.setAnimActual(Player.animaciones.get("deslizarse").getAnimacion());
					if (p.getAnimActual().isAnimationFinished(p.getTimerAnim())) {
						p.setContadorAnimacion(p.getContadorAnimacion() + 1);
						p.setTimerAnim(0);
						if (p.getContadorAnimacion() >= 2) {
							teclasPulsadas.remove((Integer) Input.Keys.SPACE);
							p.setContadorAnimacion(0);
						}
					}
				} else {
					teclasPulsadas.remove((Integer) Input.Keys.SPACE);
					p.setContadorAnimacion(0);
				}
			}
		} else {
			p.getAnimActual().setFrameDuration((float) 0.1);
			if (teclasPulsadas.contains(Input.Keys.A)) {
				p.setAnimActual(Player.animaciones.get("correr").getAnimacion());

			} else if (teclasPulsadas.contains(Input.Keys.S)) {
				p.setAnimActual(Player.animaciones.get("agacharse").getAnimacion());
			} else if (teclasPulsadas.contains(Input.Keys.D)) {
				p.setAnimActual(Player.animaciones.get("correr").getAnimacion());
			} else {
				p.setAnimActual(Player.animaciones.get("idle").getAnimacion());
			}
		}

		if (p.getBody().getVelx() > 0) {
			p.setDir(true);
		} else if (p.getBody().getVelx() < 0)
			p.setDir(false);

		if (p.getComboEspadaTimer() > 0)
			p.setComboEspadaTimer(p.getComboEspadaTimer() - 1);
		else if (teclasPulsadas.contains((Integer) Input.Keys.F)) {
			teclasPulsadas.remove((Integer) Input.Keys.F);
			DesktopLauncher.client.enviar("/up " + (Integer) Input.Keys.F);
			p.setContCombo(0);
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getComboEspadaTimer() {
		return comboEspadaTimer;
	}

	public void setComboEspadaTimer(int comboEspadaTimer) {
		this.comboEspadaTimer = comboEspadaTimer;
	}

	public static int getComboespadamaxtime() {
		return comboEspadaMaxTime;
	}

	public static Player buscarPlayer(int id) {
		Player p = null;
		for (Player temp : Juego.players) {
			if (temp.getId() == id)
				p = temp;
		}
		if (p == null)
			System.out.println("Player no encontrado");
		return p;
	}

	public long getId() {
		return id;
	}

	public void setId(long id2) {
		this.id = id2;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<Ataque> ataques) {
		this.ataques = ataques;
	}

}
