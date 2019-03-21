import processing.core.PApplet;

public class Ball {

	PApplet p;
	
	float ddy = 0.1f;
	
	float x, y;
	float radius;
	int color;
	
	float prevX, prevY;
	float dx, dy;
	
	public Ball(PApplet p) {
		this.p = p;
	}
	
	public void update() {
		prevX = x;
		prevY = y;
		
		x += dx;
		y += dy;
		dy += ddy;
		
		// bounce off sides
		dx = x+radius > p.width ? -dx : dx;
		dx = x-radius < 0 ? -dx : dx;
		x = x+radius > p.width ? p.width-radius : x;
		x = x-radius < 0 ? radius : x;
	} 
	
	public void draw() {
		//p.stroke(0);
		//p.noStroke();
		p.stroke(p.color(color));
		p.fill(p.color(color));
		p.strokeWeight(2*radius);
		p.line(prevX, prevY, x, y);
		p.ellipse(x, y, radius, radius);
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public void setColor(int c) {
		color = p.color(c);
	}
	
	public void setdx(float dx) {
		this.dx = dx;
	}
	
	public void setdy(float dy) {
		this.dy = dy;
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRadius() {
		return radius;
	}
}
