package ObjBox2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.desktop.Launcher;

public class Particula {
	public Particula() {
		TextureAtlas particleAtlas = new TextureAtlas(""); // <-load some atlas with your particle assets in
		ParticleEffect effect = new ParticleEffect();
		effect.load(Gdx.files.internal("myparticle.p"), particleAtlas);
		effect.start();

		// Setting the position of the ParticleEffect
		effect.setPosition(100, 100);

		// Updating and Drawing the particle effect
		// Delta being the time to progress the particle effect by, usually you pass in
		// Gdx.graphics.getDeltaTime();
		effect.draw(Launcher.batch, Gdx.graphics.getDeltaTime());
	}
}
