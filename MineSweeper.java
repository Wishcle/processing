import processing.core.PApplet;

public class MineSweeper extends PApplet {

	public final int s = 32;
	MineField field;
	
	int w = 40;
	int h = 20;
	int mines = 160;
	
	public static void main(String[] args) {
		main("MineSweeper");
	}
	
	public void settings() {
		size(w*s, h*s, FX2D);
		noLoop();
	}
	
	public void setup() {
		MineField.p = this;
		field = new MineField(w, h, mines);
	}
	
	public void draw() {
		field.draw();
	}
	
	@Override
	public void mousePressed() {
		int x = mouseX / s;
		int y = mouseY / s;
		MineField.MineCell cell = field.getCellAt(x, y);
		
		MineField.Action action;
		
		if (mouseButton == LEFT) {
			action = cell.leftClick();
		} else {
			action = cell.rightClick();
		}
		
		if (action == MineField.Action.EXPLODED) {
			field.reset();
		}
		
		redraw();
	}
	
	@Override
	public void keyPressed() {
		switch(key) {
			case ' ':
				field.reset();
				redraw();
				break;
			case 'X':
				field.xray = !field.xray;
				redraw();
				break;
		}
	}
	
}
