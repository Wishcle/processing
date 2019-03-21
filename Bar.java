import java.util.Objects;

import processing.core.PApplet;

public class Bar implements Comparable<Bar> {

	public static PApplet p;
	
	public float x, y;
	public float height;
	
	public Bar(float x, float y, float height) {
		this.x = x;
		this.y = y;
		this.height = height;
	}
	
	public static void swapX(Bar b1, Bar b2) {
		float tempX = b1.x;
		b1.x = b2.x;
		b2.x = tempX;
	}
	
	public void draw() {
		p.fill(255);
		p.rect(x, y, 1, height);
	}

	@Override
	public int compareTo(Bar o) {
		return (int) Math.ceil(height - o.height);
	}
	
}
