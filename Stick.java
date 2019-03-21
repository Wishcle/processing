import processing.core.PApplet;

public class Stick {

	static PApplet p;

	final float x1, y1, x2, y2;
	boolean diag;

	public Stick(float x1, float y1, float x2, float y2, boolean diag) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.diag = diag;
	}

	public Pair split(boolean left) {
		Stick s1, s2;
		float xn, yn;
		float m = mag() / 2;

		if (diag) {
			
			if (left) {
				if ((x2 > x1 && y2 > y1) || (x2 < x1 && y2 < y1)) {
					xn = x2;
					yn = y1;
				} else {
					xn = x1;
					yn = y2;
				}
			} else {
				if ((x2 > x1 && y2 > y1) || (x2 < x1 && y2 < y1)) {
					xn = x1;
					yn = y2;
				} else {
					xn = x2;
					yn = y1;
				}
			}

		} else {
			
			if (left) {
				if (x1 == x2) {
					yn = (y2+y1) / 2;
					if (y2 > y1) {
						xn = x1 + m;
					} else {
						xn = x1 - m;
					}
				} else {
					xn = (x2+x1) / 2;
					if (x2 > x1) {
						yn = y1 - m;
					} else {
						yn = y1 + m;
					}
				}
			} else {
				if (x1 == x2) {
					yn = (y2+y1) / 2;
					if (y2 > y1) {
						xn = x1 - m;
					} else {
						xn = x1 + m;
					}
				} else {
					xn = (x2+x1) / 2;
					if (x2 > x1) {
						yn = y1 + m;
					} else {
						yn = y1 - m;
					}
				}
			}
		}

		s1 = new Stick(x1, y1, xn, yn, !diag);
		s2 = new Stick(xn, yn, x2, y2, !diag);
		return new Pair(s1, s2);
	}
	
	public void draw() {
		p.line(x1, y1, x2, y2);
	}

	private float mag() {
		return (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}

	public class Pair {
		final Stick s1, s2;
		public Pair(Stick s1, Stick s2) {
			this.s1 = s1;
			this.s2 = s2;
		}
	}

}
