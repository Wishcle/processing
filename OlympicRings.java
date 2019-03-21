import processing.core.PApplet;

public class OlympicRings extends PApplet {

	Ring blue, black, red, yellow, green;
	
	public static void main(String[] args) {
		main("OlympicRings");
	}
	
	public void settings() {
		size(500, 400);
	}
	
	public void setup() {
		background(50);
		noLoop();
		
		Ring.p = this;
		blue = new Ring(150, 200, color(0, 0, 255));
		black = new Ring(250, 200, color(0));
		red = new Ring(350, 200, color(255, 0, 0));
		yellow = new Ring(200, 250, color(255, 255, 0));
		green = new Ring(300, 250, color(0, 255, 0));
	}
	
	public void draw() {
		//blue.draw();
		//black.draw();
		//red.draw();
		yellow.draw(blue);
		//green.draw();
	}
	
}
