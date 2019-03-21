import processing.core.PApplet;

public class Test extends PApplet {

	float px, py;
	float r = 20;
	
	public static void main(String[] args) {
		main("Test");
	}
	
	public void settings() {
		size(500, 500, FX2D);
		
	}
	
	public void setup() {
		surface.setResizable(false);
		background(20);
		stroke(200, 50, 50);
		strokeWeight(r);
		fill(200, 50, 50);
	}
	
	public void draw() {
		if(mousePressed) {
			float r = map(mouseX, 0, width, 255, 0);
			float g = map(mouseY, 0, height, 50, 255);
			float b = map(mouseX * mouseY, 0, width * height, 0, 255);
			stroke(r, g, b);
			line(px, py, mouseX, mouseY);
		}
		
		px = mouseX;
		py = mouseY;
	}
	
	public void keyPressed() {
		switch(key) {
		case ' ':
			background(20);
			break;
		}
	}
}