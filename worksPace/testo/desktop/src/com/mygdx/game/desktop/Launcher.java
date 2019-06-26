package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Launcher extends Game {

	public static SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		Launcher.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.setScreen(new Juego(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
