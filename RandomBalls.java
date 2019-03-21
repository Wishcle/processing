import java.util.ArrayList;

import processing.core.PApplet;

public class RandomBalls extends PApplet {

	ArrayList<Ball> balls;
	
	public static void main(String[] args) {
		main("RandomBalls");
	}
	
	// screen size
	public void settings() {
		size(1000, 700, FX2D);
	}
	
	// initialize variables
	public void setup() {
		balls = new ArrayList<Ball>();
		noStroke();
	}
	
	public void draw() {
		//background(60, 105, 180, ); // rgb
		fill(0, 20);
		rect(0, 0, width, height);
		
		// draw balls
		for(Ball b : balls) {
			b.update();
			b.draw();
		}
		
		// add more when clicked
		if(mousePressed) {
			balls.add(genBall());
		}
		
		// remove balls below screen
		for(int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if(b.getY() - b.getRadius() > height) {
				balls.remove(i);
			}
		}
	}	
	
	// generate random ball
	private Ball genBall() {
		Ball ball = new Ball(this);
		ball.setLocation(mouseX, mouseY);
		ball.setRadius(random(5, 5));
		ball.setdx(random(-10, 10));
		ball.setdy(random(-10, 0));
		float r = random(50, 255);
		float g = random(50, 255);
		float b = random(50, 255);
		int c = color(200, 50, 50);
		ball.setColor(color(c));
		return ball;
	}

}
