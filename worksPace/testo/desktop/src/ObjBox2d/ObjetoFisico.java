package ObjBox2d;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.desktop.Juego;
import com.mygdx.game.desktop.Launcher;

public abstract class ObjetoFisico {
	protected int tipo; //Tipo se refiere a que guerrero es 0,1,2 o 3
    protected Body body; //Cuerpo físico de box2d
    protected boolean animLoop = true;
    protected Boolean cameraLock = false;
    public Boolean getCameraLock() {
		return cameraLock;
	}


	public void setCameraLock(Boolean cameraLock) {
		this.cameraLock = cameraLock;
	}


	public boolean isAnimLoop() {
		return animLoop;
	}


	public void setAnimLoop(boolean animLoop) {
		this.animLoop = animLoop;
	}


	public Animation<TextureRegion> getAnimActual() {
		return animActual;
	}


	public void setAnimActual(Animation<TextureRegion> animActual) {
		this.animActual = animActual;
	}


	protected boolean dir = false;
    public boolean isDir() {
		return dir;
	}


	public void setDir(boolean dir) {
		this.dir = dir;
	}


	protected Animation<TextureRegion> animActual;
    protected float timerAnim = 0;
    protected TextureRegion currentFrame = new TextureRegion();
    protected boolean borrar = false;
    protected boolean quieto;
    protected int contadorAnimacion = 0;
    
    
    public int getContadorAnimacion() {
		return contadorAnimacion;
	}


	public void setContadorAnimacion(int contadorAnimacion) {
		this.contadorAnimacion = contadorAnimacion;
	}

	public boolean isQuieto() {
		return quieto;
	}


	public void setQuieto(boolean quieto) {
		this.quieto = quieto;
	}


	public void pintar(boolean debug) {
		if(debug) {
        	debug();
        }
		try {
			if(animActual.getKeyFrames().length>1)currentFrame.setRegion(animActual.getKeyFrame(!quieto?timerAnim+=Gdx.graphics.getDeltaTime():0,false)); //Conseguir la animacion
			else currentFrame.setRegion(animActual.getKeyFrames()[0]);
		} catch (Exception e) {
			System.out.println("Error accediendo al index: "+(!quieto?timerAnim+=Gdx.graphics.getDeltaTime():0));
			System.out.println("animacionActual: "+animActual.getKeyFrames().length+" - "+animActual.getKeyFrames()[0]);
		}
    	
    	if(animActual.isAnimationFinished(timerAnim) && animLoop){
            timerAnim=0;
        }
    	//float x = (float)(this.body.getX());
    	float x = (Juego.camera.viewportWidth / 2);
    	if(!cameraLock) x = (float)(this.body.getX())+(Juego.parallax)+(Juego.camera.viewportWidth / 2);
		Launcher.batch.draw(currentFrame,(float) (x+(this.body.getWidth()*(this.dir?0:1))),(float)this.body.getY(),currentFrame.getRegionWidth()*3*(this.dir?1:-1),currentFrame.getRegionHeight()*3); //Pintar la animacion
        if(borrar) Juego.borrar.add(this); //añadirlo a la cola de borrado si no tiene hp.
    }


	public int getTipo() {
		return tipo;
	}
	
	public void debug() {
		Juego.shapeRenderer.setColor(Color.RED);
	    Juego.shapeRenderer.rect((float)(+this.body.getX()+(Juego.parallax)+(Juego.camera.viewportWidth / 2)), (float)this.body.getY(), (float)this.body.getWidth(), (float)this.body.getHeight());
	    //System.out.println("x: "+((float)this.body.getX())+" y: "+(float)this.body.getY());
	    //System.out.println("x2: "+Juego.players.get(0).body.getX()+" parallax: "+Juego.parallax);
	    Juego.shapeRenderer.setColor(Color.BLUE);
	    Juego.shapeRenderer.rect((float)(Juego.camera.viewportWidth / 2), (float)Juego.players.get(0).body.getY(), (float)Juego.players.get(0).getBody().getWidth(), (float)Juego.players.get(0).getBody().getHeight());
	    
	    Juego.shapeRenderer.setColor(Color.GOLD);
	    Juego.shapeRenderer.rect((float)((Juego.camera.viewportWidth / 2)+Juego.players.get(0).getBody().getHitbox()[0]), (float)(Juego.players.get(0).getBody().getY()), (float)Juego.players.get(0).getBody().getHitbox()[2], (float)Juego.players.get(0).getBody().getHitbox()[3]);
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public Body getBody() {
		return body;
	}


	public void setBody(Body body) {
		this.body = body;
	}

	public float getTimerAnim() {
		return timerAnim;
	}


	public void setTimerAnim(float timerAnim) {
		this.timerAnim = timerAnim;
	}


	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}


	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}


	public boolean isBorrar() {
		return borrar;
	}


	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}
    
    
}
