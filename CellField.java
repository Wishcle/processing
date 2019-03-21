import java.util.Stack;

import processing.core.PApplet;

public class CellField {

	public static PApplet p;
	public int w, h, cellSize;
	
	boolean[][] cells;
	Stack<Cell> buffer;
	
	public CellField(int cellSize) {
		this.cellSize = cellSize;
		buffer = new Stack<>();
		reset();
	}
	
	/*
	 * 	Clears the cell field.
	 * 	(kills all the cells)
	 */
	public void reset() {
		w = p.width / cellSize;
		h = p.height / cellSize;
		cells = new boolean[h][w];
	}
	
	/*
	 * 	Generates a blinker.
	 * 	(was used for testing)
	 */
	public void genBlinker() {
		cellSize = 100;
		reset();
		cells[2][1] = true;
		cells[2][2] = true;
		cells[2][3] = true;
		buffer.push(new Cell(2, 1, true));
		buffer.push(new Cell(2, 2, true));
		buffer.push(new Cell(2, 3, true));
	}
	
	/*
	 * 	Generates a random cell field.
	 * 	(probability 0.5)
	 */
	public void genRandomField() {
		buffer.clear();
		reset();
		boolean b;
		for (short i = 0; i < cells.length; i++) {
			for (short j = 0; j < cells[i].length; j++) {
				b = Math.random() < 0.5;
				cells[i][j] = b;
				buffer.push(new Cell(i, j, b));
			}
		}
	}
	
	/*
	 * 	Executes one life cycle.
	 * 
	 * 	The neighbors around each cell are counted.
	 * 	If the cell needs to change state, it's new
	 * 	state is added to the buffer. You can't change the
	 * 	state of a cell before the others are analyzed. Later, 
	 * 	the buffer is emptied, applying all the updates at once.
	 * 
	 * 	Back in the draw() method, only the buffer
	 * 	is drawn on each loop, meaning that pixels 
	 * 	are only redrawn when they need to be.
	 */
	public void lifeCycle() {
		buffer.clear();
		boolean alive;
		int n;
		for (short i = 0; i < cells.length; i++) {
			for (short j = 0; j < cells[i].length; j++) {
				alive = cells[i][j];
				n = countNeighbors(i, j);
				
				if (alive) {
					if (n < 2 || n > 3) {
						Cell p = new Cell(i, j, false);
						buffer.push(p);
					}
				} else { // if dead ..
					if (n == 3) {
						Cell p = new Cell(i, j, true);
						buffer.push(p);
					}
				}
			}
		}
	}
	
	/*
	 * 	Empties the buffer, updating the cells.
	 */
	public void applyBuffer() {
		Cell p;
		while (!buffer.isEmpty()) {
			p = buffer.pop();
			cells[p.y][p.x] = p.b;
		}
	}
	
	/*
	 * 	@return The number of surrounding (alive) cells.
	 */
	public int countNeighbors(short i, short j) {
		int n = 0;
		if (cells[m(i-1, h)][m(j-1, w)]) n++;
		if (cells[m(i-1, h)][j])         n++;
		if (cells[m(i-1, h)][m(j+1, w)]) n++;
		if (cells[i]        [m(j-1, w)]) n++;
		if (cells[i]        [m(j+1, w)]) n++;
		if (cells[m(i+1, h)][m(j-1, w)]) n++;
		if (cells[m(i+1, h)][j])         n++;
		if (cells[m(i+1, h)][m(j+1, w)]) n++;
		return n;
	}
	
	/*
	 *	Helper method for countNeighbors().
	 *
	 *	Prevents out-of-bounds errors.
	 */
	private int m(int i, int m) {
		i %= m;
		if (i < 0) {i += m;}
		return i;
	}
	
	/*
	 * 	Stores the indices of a particular cell,
	 * 	along with the new state of that cell.
	 */
	class Cell {
		public short x, y;
		public boolean b;
		
		public Cell(int i, int j, boolean b) {
			y = (short) i;
			x = (short) j;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "pair {"+x+", "+y+"}: "+b;
		}
	}
	
}
