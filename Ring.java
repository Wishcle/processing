import processing.core.PApplet;

public class Ring {

	public static PApplet p;

	public static float r1 = 45;
	public static float r2 = 35;

	int color;
	float x, y;

	public Ring(float x, float y, int color) {
		this.x = x;
		this.y = y;		
		this.color = color;
	}

	public boolean inRing(float x1, float y1) {
		float d = dist(x, y, x1, y1);
		if (r2 <= d && d <= r1) {
			return true;
		} else {
			return false;			
		}
	}

	private float dist(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
	}

	public void draw() {
		for (int j = (int) (y-r1); j < y+r1; j++) {
			for (int i = (int) (x-r1); i < x+r1; i++) {
				if (inRing(i, j)) {
					p.set(i, j, color);					
				}
			}
		}
	}

	public void draw(Ring over) {
		for (int j = (int) (y-r1); j < y+r1; j++) {
			for (int i = (int) (x-r1); i < x+r1; i++) {
				if (inRing(i, j) && !over.inRing(i, j)) {
					p.set(i, j, color);					
				}
			}
		}
	}
}
