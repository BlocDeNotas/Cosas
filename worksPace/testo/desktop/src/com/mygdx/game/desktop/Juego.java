package com.mygdx.game.desktop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import Cliente.Client;
import Cliente.Login;
import FisicasComunes.Body;
import ObjBox2d.Ataque;
import ObjBox2d.Player;

public class Juego extends ApplicationAdapter implements Screen, InputProcessor {
	Texture img;
	int[] pf;
	
	public static Launcher game;
	
	Music mus;
	static Calendar cal = Calendar.getInstance();
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public static OrthographicCamera camera;
	public BitmapFont font;
	private final int parallaxSpeed = 6;
	static float tmFrame;
	
	public static ArrayList<Sprite> fondos = new ArrayList<Sprite>();
	public static ShapeRenderer shapeRenderer;
	public static ArrayList<Ataque> ataques = new ArrayList<Ataque>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Object> borrar = new ArrayList<Object>();
	
	public static int x = 0;
	
	public static int y = 0;
	public final static int ySuelo = 50;
	public static int parallax = 0;

	public Juego(Launcher game) {
		font = new BitmapFont();
	    font.setColor(Color.RED);
		Juego.game = game;
		Juego.camera = new OrthographicCamera();
		Juego.camera.setToOrtho(false, 928, 583);

		shapeRenderer = new ShapeRenderer(); // Sirve para pintar formas como rectangulos o circulos.
		/* ========================================================================= */
		/*
		 * Música
		 * =========================================================================
		 */

		mus = Gdx.audio.newMusic(Gdx.files.internal("musicacheta.mp3"));
		mus.setLooping(true);
		mus.play();
		/* ========================================================================= */
		/*
		 * Imagenes
		 * =========================================================================
		 */
		for (int i = 0; i < 10; i++)
			fondos.add(new Sprite(new Texture(Gdx.files.internal("fondook" + i + ".png"))));
		pf = new int[fondos.size()];
		for (int i = 0; i < pf.length - 1; i++)
			pf[i] = 0;

		CargarRecursos.cargar();
		/* ========================================================================= */

		/* ======================================================================= */
		/* Getenrar animaciones: Se genera una animacion para cada tipo de guerrero. */
		/*
		 * =============================================================================
		 * ===============================================
		 */

		/*
		 * =============================================================================
		 * =============================================================
		 */
		tmFrame = 0f; // Variable que controlará en que frame está cada animación.
		
		Gdx.input.setInputProcessor(this); // Procesador de input, touch,click etc...
		players.add(new Player(100, 1, 0));
		players.get(0).setCameraLock(true);
		
		players.get(0).getAtaques().add(new Ataque(10, 0, 1,180,60));
		shapeRenderer.setAutoShapeType(true);
		controlesSinLoop.add(Input.Keys.F);
		controlesSinLoop.add(Input.Keys.SPACE);
		players.get(0).setId(DesktopLauncher.id);
	}

	@Override
	public void create() {

	}

	@Override
	public void render(float delta) {
		for (String[] playerNuevo : playerCola) {
			crearPlayerNetworking(playerNuevo);
		}
		playerCola.clear();
		shapeRenderer.setProjectionMatrix(Launcher.batch.getProjectionMatrix()); // Crear esto si no está creado para el
		parallax = (int) players.get(0).getBody().getX()*-1;													// shaperenderer...
		shapeRenderer.begin();
		Player p = players.get(0);
		
		tmFrame += Gdx.graphics.getDeltaTime(); // Cambiar la animacion de cada objeto.
		/* ========================================== */
		/*
		 * Vaciar lo del anterior render =========================================
		 */
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/* ========================================== */
		/*
		 * Empezar a pintar ==============================================
		 */
		Launcher.batch.setProjectionMatrix(Juego.camera.combined);
		Launcher.batch.begin();
		int[] xf = new int[fondos.size()];

		for (int i = fondos.size() - 1; i >= 0; i--) {
			xf[i] = parallax / (i + 1) + 928 * pf[i];
			if (xf[i] <= -928) {
				xf[i] = 0;
				pf[i] += 1;
			} else if (xf[i] >= 928) {
				xf[i] = 0;
				pf[i] -= 1;
			}
			Launcher.batch.draw(fondos.get(i), xf[i], 0); // Pintar el suelo y las montañas de fondo
			Launcher.batch.draw(fondos.get(i), xf[i] + 928 * (xf[i] > 0 ? -1 : 1), 0); // Pintar el suelo y las montañas																			// de fondo
		}
		int num = 100;
		for (Player pl : players) {
			pl.update();
			for (Ataque aActual : pl.getAtaques()) {
				double xt = aActual.getBody().getX();
				double yt = aActual.getBody().getY();
				boolean colis = false;
				for (Player pl2 : players) {
					Body pl2Body = pl2.getBody();
					double xPl = pl2Body.getHitbox()[4];
					double yPl = pl2Body.getHitbox()[5];
					//if(!pl2.equals(pl)) {
						if (!p.equals(aActual)) {
							int distx = (int) Math.abs(xt - xPl);
							if ( (xt<xPl && distx < aActual.getBody().getWidth()) || (xt>xPl && distx < pl2Body.getHitbox()[2])) {
								int disty = (int)Math.abs(yPl-yt);
								
								if( (yt < yPl && disty < aActual.getBody().getHeight()) || (yt>yPl && disty < pl2Body.getHitbox()[3])) {
									colis = true;
									pl2.setHp(pl2.getHp() - aActual.getAtk());
								}
							}

						}
					}
			}
			font.draw(Launcher.batch, String.valueOf(pl.getBody().getX()), 200, num);
			num+=100;
			
		}
		/* ============================================================= */
		/*
		 * Borrar los mobs por separado en una funcion propia para evitar
		 * concurrentModificationExcption.
		 * =============================================================================
		 * ================
		 */
		for (Ataque borrarObj : ataques) {
			ataques.remove(borrarObj);
		}
		borrar.clear();
		
		
		
		Launcher.batch.end(); // Ya no se va a pintar nada más (Excepto los rectangulos de vida con el
								// shapeRenderer)
		shapeRenderer.end();
	}

	private void crearPlayerNetworking(String[] playerNuevo) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(playerNuevo[3])!=DesktopLauncher.id) {
			System.out.println("CREANDO PLAYER NUEVO DESDE NETWORKING");
			Player temp = new Player(100,1,Integer.parseInt((playerNuevo[3])));//Integer.parseInt(comando[0]),Integer.parseInt(comando[1]),Integer.parseInt(comando[2])); //x,y,id
			players.add(temp);
			temp.setCameraLock(false);
		}
	}

	@Override
	public void dispose() {
		Launcher.batch.dispose();
		img.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (!players.get(0).teclasPulsadas.contains(keycode)) {
			players.get(0).teclasPulsadas.add((Integer) keycode);
			DesktopLauncher.client.enviar(""+keycode);
			if(controlesSinLoop.contains(keycode)) players.get(0).setTimerAnim(0);
		}
			
		return false;
	}
	
	public static ArrayList<Integer>  controlesSinLoop = new ArrayList<Integer>();

	@Override
	public boolean keyUp(int keycode) {
		if (players.get(0).teclasPulsadas.contains(keycode)) {
			DesktopLauncher.client.enviar("/up "+keycode);
			if(!controlesSinLoop.contains(keycode)) {
				players.get(0).teclasPulsadas.remove((Integer) keycode);
				
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	private static ArrayList<String[]> playerCola = new ArrayList<String[]>();
	public static void print(String msg) {
		String dat = sdf.format(cal.getTime());
		//System.out.println(dat+": "+msg);
		if(msg.charAt(0)=='/') { //Comando
			String[] comando = msg.split(" ");
			if(comando[0].equals("/create")) {
				playerCola.add(comando);
			} else if(comando[0].equals("/input")){
				buscarPlayer(Long.parseLong(comando[2])).teclasPulsadas.add(Integer.parseInt(comando[1]));
			} else if(comando[0].equals("/up")){
				buscarPlayer(Long.parseLong(comando[2])).teclasPulsadas.remove((Integer)Integer.parseInt(comando[1]));
			} else if(comando[0].equals("/syncro")){
				Player temp = buscarPlayer(Long.parseLong(comando[3]));
				if(temp != null) {
					temp.getBody().setX(Double.parseDouble(comando[1]));
					temp.getBody().setY(Double.parseDouble(comando[2]));
					temp.getBody().setVelx(Double.parseDouble(comando[4]));
					temp.getBody().setVely(Double.parseDouble(comando[5]));
				}
				
			}
			
		}
	}
	
	public static Player buscarPlayer(long id) {
		for (Player temp : players) {
			if(temp.getId()==id) return temp;
		}
		//System.out.println("PLAYER NO ENCONTRADO");
		return null;
	}
}
