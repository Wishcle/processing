import processing.core.PApplet;

public class Square {

	static PApplet p;
	static float t;
	static float dx, dy;
	
	float x, y;
	int c;
	
	float nx, ny, nz;
	float inc = 0.005f;
	
	public Square(float x, float y) {
		this.x = x;
		this.y = y;
		nx = x * inc;
		ny = y * inc;
		c = genColor();
		setSpeed(0, 0);
	}
	
	public static void set(PApplet p, float t) {
		Square.p = p;
		Square.t = t;
	}
	
	public static void setSpeed(float dx, float dy) {
		Square.dx = dx;
		Square.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
		
		// off-screen
		if(x > p.width + t) {
			x -= p.width + 2*t;
			nx -= 0.5 * inc * (p.width + 2*t);
			c = genColor();
		} else if(x < -t) {
			x += p.width + 2*t;
			nx += 0.5 * inc * (p.width + 2*t);
			c = genColor();
		}
		
		if(y > p.height + t) {
			y -= p.height + 2*t;
			ny -= inc * (p.height + 2*t);
			c = genColor();
		} else if(y < -t) {
			y += p.height + 2*t;
			ny += inc * (p.height + 2*t);
			c = genColor();
		}
	}
	
	public void show() {
//		p.pushStyle();
		p.noStroke();
		p.fill(c);
		p.rect(x, y, t, t);
//		p.popStyle();
	}
	
	public int genColor() {
		float r = 255;
//		float g = p.noise(-255) * 255;
		float g = p.noise(nx, ny) * 255;
		float b = 0;
		return p.color(r, g, b);
	}
	
	
}
