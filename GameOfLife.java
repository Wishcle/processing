import processing.core.PApplet;

public class GameOfLife extends PApplet {

	/*
	 * 	== Conway's Game of Life : simulation ==
	 * 
	 * 	-- Controls --
	 * 		L:		loop
	 * 		SPACE: 	step / pause loop
	 * 		ENTER:	generate new field 
	 * 
	 * 	-- How does it work? ---
	 * 	Each 'cell' has 8 neighboring cells. A cell has
	 * 	two states: alive and dead.
	 * 
	 * 	An alive cell dies when it has less than 2 neighbors (by isolation)
	 *  or greater than 3 neighbors (by overpopulation).
	 * 
	 * 	A dead cell is born if it has exactly 3 neighbors.
	 * 
	 */
	
	CellField field;
	short s = 1;
	
	public static void main(String[] args) {
		main("GameOfLife");
	}
	
	public void settings() {
		size(1200, 800);
		pixelDensity(2);
	}
	
	public void setup() {
		frameRate(30);
		background(20);
		noStroke();
		fill(255);
		noLoop();
		
		CellField.p = this;
		field = new CellField(s);
		field.genRandomField();
		field.applyBuffer();
	}
	
	public void draw() {
		// draw cell buffer
		field.buffer.forEach(p -> {
			int c = p.b ? 255 : 20;
			fill(c);
			rect(p.x*s, p.y*s, s, s);
		});
		
		// update cell[][] state
		field.applyBuffer();
		field.lifeCycle();
	}
	
	public void keyPressed() {
		switch (key) {
			case ' ':
				noLoop();
				redraw();
				break;
			case 10:
				field.genRandomField();
				break;
			case 'l':
				loop();
				break;
		}
	}
	
	
	
}
