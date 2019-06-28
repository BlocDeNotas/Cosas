package FisicasComunes;

public class Body {
	private double x, y, velx, vely;
	double width = 10;
	private double height = 10;
	private double[] hitbox = new double[6];

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height * 3;
	}

	public void setWidth(int width) {
		this.width = width * 3;
	}

	public double getX() {
		return x;
	}

	public void setX(double xt) {
		this.x = xt;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelx() {
		return velx;
	}

	public void setVelx(double velx) {
		this.velx = velx;
	}

	public double getVely() {
		return vely;
	}

	public void setVely(double vely) {
		this.vely = vely;
	}

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Body(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.velx = 0;
		this.vely = 0;
		this.width = width * 3;
		this.height = height * 3;
		this.hitbox[0] = +this.width / 4;
		this.hitbox[1] = 0;
		this.hitbox[2] = this.width / 2;
		this.hitbox[3] = this.height * .75;
		this.hitbox[4] = this.x + this.hitbox[0];
		this.hitbox[5] = this.x + this.hitbox[1];
	}

	public double[] getHitbox() {
		return hitbox;
	}

	public void setHitbox(double[] hitbox) {
		this.hitbox = hitbox;
	}

	public void update() {
		this.hitbox[4] = this.x + this.hitbox[0];
		this.hitbox[5] = this.y + this.hitbox[1];
		// this.y += this.vely;
		// this.x += this.velx;
	}

	public double getWidth() {
		return this.width;
	}
}