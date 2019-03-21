import processing.core.PApplet;

public class Particle {

	static PApplet p;
	
	Vector pos, vel, acc;
	
	public Particle(float x, float y) {
		pos = new Vector(x, y);
		vel = new Vector();
		acc = new Vector();
	}
	
	public static void givePApplet(PApplet p) {
		Particle.p = p;
	}
	
	public void update() {
		
	}
	
	public void draw() {
		
	}
	
	
	
}
