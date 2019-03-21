import processing.core.PApplet;

public class RubiksCube extends PApplet {

	float xt = -PI/8;
	float yt, ytinc = -PI/128f;
	
	public static void main(String[] args) {
		main("RubiksCube");
	}
	
	public void settings() {
		size(600, 500, P3D);
		pixelDensity(2);
	}
	
	public void setup() {
		//noStroke();
		stroke(255);
		strokeWeight(2);
		noFill();
	}
	
	public void draw() {
		background(20);
		translate(width/2, height/2);
		lights();
		
		rotateX(-PI/8);
		rotateY(yt);
		
		box(150);
		
		createShape();
		
		yt += ytinc;
		yt %= TWO_PI;
	}

}
