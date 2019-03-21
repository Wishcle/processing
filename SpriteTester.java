import processing.core.PApplet;

public class SpriteTester extends PApplet {

	Sprite sprite;
	int color, newColor;
	
	String[] hex = {
		"78A55887",
		"01324548"
	};
	
	String[] hex1 = {
		"10101010",
		"30102402"
	};
	
	public static void main(String[] args) {
		main("SpriteTester");
	}
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		background(30);
		noLoop();
		
		sprite = new Sprite(8, 8, this);
		sprite.addLayer(color = color(47, 200, 132), hex);
		sprite.addLayer(color = color(150, 67, 85), hex1);
	}
	
	public void draw() {
		background(30);
		
		newColor = color(random(255), random(255), random(255));
		sprite.updateLayer(color, newColor);
		color = newColor;
		
		sprite.draw((int) random(width/2), (int) random(height/2), (int) random(40));
	}
	
	public void keyPressed() {
		if (key == ' ') {
			redraw();
		}
	}

}
