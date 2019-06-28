package com.mygdx.game.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import ObjBox2d.Ataque;
import ObjBox2d.DatosAnimacion;
import ObjBox2d.Player;

public final class CargarRecursos {

	public static HashMap<Object[], HashMap<String, DatosAnimacion>> animPorEntidades = new HashMap<Object[], HashMap<String, DatosAnimacion>>();

	public static final void cargar() {
		Settings settings = new Settings();
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		TexturePacker.process(settings, "../core/assets", "../core/assets", "game");
		TextureAtlas imagenes = new TextureAtlas("../core/assets/game.atlas");

		// Player = 52x39
		Player.animaciones.put("idle", new DatosAnimacion(0, 3));
		Player.animaciones.put("agacharse", new DatosAnimacion(4, 7));
		Player.animaciones.put("correr", new DatosAnimacion(8, 13));
		Player.animaciones.put("iniciarSalto", new DatosAnimacion(14, 17));
		Player.animaciones.put("rodarAire", new DatosAnimacion(18, 21));
		Player.animaciones.put("caer", new DatosAnimacion(22, 23));
		Player.animaciones.put("deslizarse", new DatosAnimacion(24, 28));
		Player.animaciones.put("agarrarseSaliente", new DatosAnimacion(29, 32));
		Player.animaciones.put("sneaky", new DatosAnimacion(30, 37));
		Player.animaciones.put("idleEspada", new DatosAnimacion(31, 41));
		Player.animaciones.put("ataque1", new DatosAnimacion(44, 46));
		Player.animaciones.put("ataque2", new DatosAnimacion(47, 52));
		Player.animaciones.put("ataque3", new DatosAnimacion(53, 58));
		Player.animaciones.put("bloquear", new DatosAnimacion(59, 64));
		Player.animaciones.put("morir", new DatosAnimacion(65, 68));
		// Player.animaciones.put("desenvainar", new DatosAnimacion(4, 16));
		// Player.animaciones.put("envainar", new DatosAnimacion(4, 17));

		Ataque.animaciones.put("normal", new DatosAnimacion(0, 0));

		Object[] playerData = { "player", imagenes.findRegion("adventurer"), 7, 16 };
		Object[] pinchoData = { "pincho", imagenes.findRegion("spikes"), 1, 1 };

		animPorEntidades.put(playerData, Player.animaciones);
		animPorEntidades.put(pinchoData, Ataque.animaciones);

		for (Entry<Object[], HashMap<String, DatosAnimacion>> entidad : animPorEntidades.entrySet()) {
			/*
			 * System.out.println(entidad.getKey()[0]+", Spritesheet = '"+entidad.getKey()[1
			 * ]+"'  ->"); System.out.println("    Cargando Animaciones: ");
			 * System.out.println("===============================");
			 */
			TextureRegion[][] imagenesSueltas = ((AtlasRegion) entidad.getKey()[1]).split(
					((AtlasRegion) entidad.getKey()[1]).getRegionWidth() / (Integer) entidad.getKey()[2],
					(((AtlasRegion) entidad.getKey()[1]).getRegionHeight()) / (Integer) entidad.getKey()[3]);
			// TextureRegion[][] imagenesSueltas = TextureRegion.split(spritesheet,
			// (spritesheet.getWidth() / (Integer)entidad.getKey()[2]),
			// (spritesheet.getHeight())/(Integer)entidad.getKey()[3]);
			// System.out.println("wImagen: "+spritesheet.getWidth() /
			// Player.rowSpritesheet);
			for (HashMap.Entry<String, DatosAnimacion> animacion : animPorEntidades.get(entidad.getKey()).entrySet()) {
				// System.out.println(animacion.getKey());
				TextureRegion[] tempAnim = new TextureRegion[(-animacion.getValue().getFramesInicio()
						+ animacion.getValue().getFamesFin()) + 1];
				// System.out.println("Inicio: "+animacion.getValue().getFramesInicio()+" Fin:
				// "+animacion.getValue().getFamesFin());
				// System.out.println("nº frames =
				// "+((-animacion.getValue().getFramesInicio()+animacion.getValue().getFamesFin())+1));
				int acumPrueba = 0;
				ArrayList<Integer> numImg = new ArrayList<Integer>();
				// System.out.println(imagenesSueltas.length);
				for (int i = 0; i < imagenesSueltas.length; i++) {
					TextureRegion[] filaSpriteSheet = imagenesSueltas[i];
					for (int j = 0; j < filaSpriteSheet.length; j++) {
						TextureRegion imagenAnimacion = filaSpriteSheet[j];
						int numeroImagen = i * 7 + j;
						if (numeroImagen >= animacion.getValue().getFramesInicio()
								&& numeroImagen <= animacion.getValue().getFamesFin()) {
							tempAnim[acumPrueba] = imagenAnimacion;
							acumPrueba++;
							numImg.add(numeroImagen);
						}
					}
				}
				// System.out.println("Animaciones ^, nºFrames: "+acumPrueba+"
				// FramesEncontrados: "+numImg.toString());
				animacion.getValue().setAnimacion(new Animation<TextureRegion>(0.075f, tempAnim));
				// System.out.println(animacion.getValue().getAnimacion().getKeyFrames()[0].getRegionWidth());
				// System.out.println("============================");
			}
			/*
			 * System.out.println("Fin carga de "+entidad.getKey()[0]);
			 * System.out.println("===============================");
			 */
		}
		try {
			DesktopLauncher.client.enviar("/createPlayer");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static final Animation<TextureRegion> cargarAnimacion(String rutaTextura, int nFrames) {
		Texture t = new Texture(Gdx.files.internal(rutaTextura));
		TextureRegion[] tmp = TextureRegion.split(t, t.getWidth() / nFrames, t.getHeight())[0];
		return new Animation<TextureRegion>(0.075f, tmp);
	}
}
