import processing.core.PApplet;

public class Vector {

	static PApplet p;
	
	float x, y;
	float maxSpeed;
	
	public Vector() {
		x = 0;
		y = 0;
		maxSpeed = 0;
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
		maxSpeed = 0;
	}
	
	public static void givePApplet(PApplet p) {
		Vector.p = p;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(float dx, float dy) {
		x += dx;
		y += dx;
	}
	
	public void setMaxSpeed(float m) {
		maxSpeed = m;
	}
	
	public void maxSpeedCheck() {
		if(maxSpeed > 0) {
			float mag = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			if(mag > maxSpeed) {
				x /= mag; x *= maxSpeed;
				y /= mag; y *= maxSpeed;
			}
		}
	}
	
}
