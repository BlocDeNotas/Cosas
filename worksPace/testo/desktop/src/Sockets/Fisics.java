package Sockets;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.mygdx.game.desktop.DesktopLauncher;

import ObjBox2d.Ataque;
import ObjBox2d.Body;

public class Fisics {
	public static void update() {
		int ySuelo = 50;
		int parallaxSpeed = 6;
		for(Cliente u: NodeJsEcho.clientes) {
			if(u.getUsuario()!= null) {
				Player p = u.getUsuario().getP();
				ArrayList<Integer> teclasPulsadas = p.teclasPulsadas;
				Body pBody = p.getBody();
				if(teclasPulsadas.contains(Input.Keys.F)) {
					pBody.setVelx(p.isDir()?1:-1);
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
				if(teclasPulsadas.contains(Input.Keys.W) && pBody.getY()<=ySuelo) {
					pBody.setVely(8);
				}else {
					if(pBody.getY()>ySuelo)pBody.setVely(pBody.getVely()-0.2);
					else {
						pBody.setVely(0);
						pBody.setY(ySuelo);
					}
				}
				pBody.setY(Math.max(pBody.getY() + pBody.getVely(),ySuelo));
				pBody.setX(pBody.getX() + pBody.getVelx());
			}
			
		}
	}
}
