import processing.core.PApplet;

public class GridBox {

	public static PApplet p;

	// pointers
	GridBox right;
	GridBox diag;
	GridBox down;

	int x, y, s;

	GridBox (int x, int y, int s) {
		this.x = x;
		this.y = y;
		this.s = s;
	}

	void display() {
		p.rect(x, y, s, s);
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void setRight(GridBox b) {
		right = b;
	}

	void setDiag(GridBox b) {
		diag = b;
	}

	void setDown(GridBox b) {
		down = b;
	}

	void drawLineToRight() {
		int m = s/2;
		int nx = right.getX();
		int ny = right.getY();
		p.line(x+m, y+m, nx+m, ny+m);
	}

	void drawLineToDiag() {
		int m = s/2;
		int nx = diag.getX();
		int ny = diag.getY();
		p.line(x+m, y+m, nx+m, ny+m);
	}

	void drawLineToDown() {
		int m = s/2;
		int nx = down.getX();
		int ny = down.getY();
		p.line(x+m, y+m, nx+m, ny+m);
	}

	// could be condensed with enums
	// assumes valid path, breaks if not
	void drawLine(String path) {
		if(path.length() == 0) {
			return;
		}

		String l = path.substring(0, 1);
		String rest = path.substring(1);

		switch(l) {
		case "R":
			drawLineToRight();
			right.drawLine(rest);
			break;
		case "G":
			drawLineToDiag();
			diag.drawLine(rest);
			break;
		case "D":
			drawLineToDown();
			down.drawLine(rest);
			break;
		}
	}

}