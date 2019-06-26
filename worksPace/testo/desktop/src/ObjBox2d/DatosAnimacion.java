package ObjBox2d;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DatosAnimacion {
	private int famesFin, framesInicio;
	private Texture texture;
	private Animation<TextureRegion> animacion;
	
	public DatosAnimacion(int framesInicio, int famesFin, Texture texture, Animation<TextureRegion> animacion) {
		super();
		this.framesInicio = framesInicio;
		this.famesFin = famesFin;
		this.texture = texture;
		this.animacion = animacion;
	}

	public DatosAnimacion(int framesInicio, int famesFin) {
		super();
		this.famesFin = famesFin;
		this.framesInicio = framesInicio;
	}

	

	public int getFamesFin() {
		return famesFin;
	}

	public void setFamesFin(int famesFin) {
		this.famesFin = famesFin;
	}

	public int getFramesInicio() {
		return framesInicio;
	}

	public void setFramesInicio(int framesInicio) {
		this.framesInicio = framesInicio;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Animation<TextureRegion> getAnimacion() {
		return animacion;
	}

	public void setAnimacion(Animation<TextureRegion> animacion) {
		this.animacion = animacion;
	}

}
