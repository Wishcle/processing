import processing.core.PApplet;

public class Grid {

	public static PApplet p;

	GridBox[][] boxes;

	int rows, cols, s;

	Grid(int rows, int cols, int s) {
		this.rows = rows;
		this.cols = cols;

		boxes = new GridBox[rows][cols];

		// make GridBoxes
		for (int i = 0; i < rows; i++) { 
			for (int j = 0; j < cols; j++) {
				boxes[i][j] = new GridBox(s*j, s*i, s);
			}
		}

		// finds and assigns pointers
		for (int i = 0; i < rows; i++) { 
			for (int j = 0; j < cols; j++) {
				// for each box b...
				GridBox b = boxes[i][j];
				
				GridBox right = null;
				GridBox diag = null;
				GridBox down = null;

				// find boxes to point to
				try {
					right = boxes[i][j + 1];
				} catch (ArrayIndexOutOfBoundsException e) {
					// do nothing
				}

				try {
					diag = boxes[i + 1][j + 1];
				} catch (ArrayIndexOutOfBoundsException e) {
					// do nothing
				}

				try {
					down = boxes[i + 1][j];
				} catch (ArrayIndexOutOfBoundsException e) {
					// do nothing
				}

				// assign pointers to b
				b.setRight(right);
				b.setDiag(diag);
				b.setDown(down);
			}
		}
	}

	void display() {
		for (int i = 0; i < rows; i++) { 
			for (int j = 0; j < cols; j++) {
				boxes[i][j].display();
			}
		}
	}
	
	void drawPath(String path) {
		boxes[0][0].drawLine(path);
	}

}
