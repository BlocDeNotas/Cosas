package FisicasComunes;

import java.util.ArrayList;

import com.badlogic.gdx.Input;

import Sockets.Cliente;
import Sockets.NodeJsEcho;

public class Fisics {
	public static void update() {
		int ySuelo = 50;
		int parallaxSpeed = 6;
		for (Cliente u : NodeJsEcho.clientes) {
			if (u.getUsuario() != null && u.getUsuario().getP() != null) {
				FisicasComunes.PlayerComun p = u.getUsuario().getP();
				calcularHitBoxes(p);
				ArrayList<Integer> teclasPulsadas = p.teclasPulsadas;
				Body pBody = p.getBody();
				if (teclasPulsadas.contains(Input.Keys.F)) {
					pBody.setVelx(p.isDir() ? 1 : -1);
				} else if (teclasPulsadas.contains(Input.Keys.A)) {
					pBody.setVelx(-parallaxSpeed);
					p.setDir(false);
				} else if (teclasPulsadas.contains(Input.Keys.S)) {
					pBody.setVelx(0);
				} else if (teclasPulsadas.contains(Input.Keys.D)) {
					p.setDir(true);
					pBody.setVelx(+parallaxSpeed);
				} else {
					pBody.setVelx(0);
				}
				if (teclasPulsadas.contains(Input.Keys.W) && pBody.getY() <= ySuelo) {
					pBody.setVely(8);
				} else {
					if (pBody.getY() > ySuelo)
						pBody.setVely(pBody.getVely() - 0.2);
					else {
						pBody.setVely(0);
						pBody.setY(ySuelo);
					}
				}
				pBody.setY(Math.max(pBody.getY() + pBody.getVely(), ySuelo));
				pBody.setX(pBody.getX() + pBody.getVelx());
			}

		}

	}

	private static void calcularHitBoxes(PlayerComun pl2) {
		ArrayList<AtaqueComun> ataquesBorrar = new ArrayList<AtaqueComun>();
		for (FisicasComunes.AtaqueComun aActual : pl2.getAtaques()) {
			aActual.setHp(aActual.getHp()-1);
			if(aActual.getHp()>0) {
				aActual.getBody().setX(pl2.getBody().getX());
				aActual.getBody().setY(pl2.getBody().getY());
				double xt = aActual.getBody().getX();
				double yt = aActual.getBody().getY();
				boolean colis = false;
				for (Cliente c2 : NodeJsEcho.clientes) {
					Body pl2Body = pl2.getBody();
					double xPl = pl2Body.getHitbox()[4]; 
					double yPl = pl2Body.getHitbox()[5]; 
					if(!pl2.equals(c2.getUsuario().getP())) { 
						int distx = (int) Math.abs(xt - xPl); 
						if ( (xt<xPl && distx < aActual.getBody().getWidth()) || (xt>xPl && distx < pl2Body.getHitbox()[2])) { 
							int disty = (int)Math.abs(yPl-yt);
							if( (yt < yPl && disty < aActual.getBody().getHeight()) || (yt>yPl && disty < pl2Body.getHitbox()[3])) { 
								colis = true;
								aActual.setHp(0);
								pl2.setHp(pl2.getHp() - aActual.getAtk()); 
							} 
						} 
					} 
				}
			} else {
				ataquesBorrar.add(aActual);
			}
		}
		for (AtaqueComun ataqueComun : ataquesBorrar) {
			pl2.getAtaques().remove(ataqueComun);
			System.out.println("Ataque borrado");
		}
	}
}
