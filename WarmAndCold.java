import processing.core.PApplet;

public class WarmAndCold extends PApplet {

	// background speed depends on the cursor's distance from center
	final float spp = 0.1f;
	
	float scl = 100;
	float t;
	
	Square[][] squares;
	
	public static void main(String[] args) {
		main("WarmAndCold");
	}
	
	public void settings() {
		size(500, 500);
		Square.setSpeed(-5, 0);
	}
	
	public void setup() {
		// square size
		t = width / scl;
		Square.set(this, t);
		
		// square array creation
		int wscl = floor(width / t);
		int hscl = floor(height / t);
		squares = new Square[wscl+2][hscl+2];
		
		for(int i = -1; i < wscl+1; i++) {
			for(int j = -1; j < hscl+1; j++) {
				squares[i+1][j+1] = new Square(i*t, j*t);
			}
		}
	}
	
	public void draw() {
		// mouse control
		float dx = mouseX - width/2;
		float dy = mouseY - height/2;
		dx *= spp;
		dy *= spp;
		Square.setSpeed(dx, dy);
		
		for(Square[] s : squares) {
			for(Square sq : s) {
				sq.update();
				sq.show();
			}
		}
	}

}
