import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Box {

	static PApplet p;
	
	PVector pos;
	float r;

	Box(float x, float y, float z, float r) {
		pos = new PVector(x, y, z);
		this.r = r;
	}
	
	public static void givePApplet(PApplet p) {
		Box.p = p;
	}

	public ArrayList<Box> generate() {
		ArrayList<Box> boxes = new ArrayList<Box>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				for (int z = -1; z < 2; z++) {
					int sum = PApplet.abs(x) + PApplet.abs(y) + PApplet.abs(z);
					float newR = r/3;
					if (sum > 1) {
						Box b = new Box(pos.x+x*newR, pos.y+y*newR,pos.z+z*newR, newR);
						boxes.add(b);
					}
				}
			}
		}
		return boxes;
	}

	public void show() {
		p.pushMatrix();
		p.translate(pos.x, pos.y, pos.z);
		p.noStroke();
		p.fill(255);
		p.box(r);
		p.popMatrix();
	}
}