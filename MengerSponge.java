import java.util.ArrayList;

import processing.core.PApplet;

public class MengerSponge extends PApplet {

	float a = 0;
	ArrayList<Box> sponge;
	
	public static void main(String[] args) {
		main("MengerSponge");
	}

	public void settings() {
		size(400, 400, P3D);
	}
	
	public void setup() {
		Box.givePApplet(this);

		// An array of Box objects
		sponge = new ArrayList<Box>();

		// Star with one
		Box b = new Box(0f, 0f, 0f, 200f);
		sponge.add(b);
	}
	public void mousePressed() {
		// Generate the next set of boxes
		ArrayList<Box> next = new ArrayList<Box>();
		for (Box b : sponge) {
			ArrayList<Box> newBoxes = b.generate();
			next.addAll(newBoxes);
		}
		sponge = next;
	}

	public void draw() {
		background(51);
		stroke(255);
		noFill();
		lights();
		translate(width/2, height/2);
		rotateX(a);
		rotateY(a*0.4f);
		rotateZ(a*0.1f);
		// Show what you've got!
		for (Box b : sponge) {
			b.show();
		}
		a += 0.01;
	}

}