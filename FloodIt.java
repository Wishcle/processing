import processing.core.PApplet;

public class FloodIt extends PApplet {

	static int w = 15;	// grid width
	static int h = 15;	// grid height
	static int s = 40;	// cube size
	static int f = 25;	// frame thickness
	static int b = 50;	// border width
	static int c = 70;	// color box size

	int[] colors = {
			color(200, 0, 0),
			color(200, 200, 0),
			color(0, 200, 0),
			color(0, 200, 200),
			color(0, 0, 200),
			//color(200, 0, 200),
	};
	
	FloodGrid grid;

	public static void main(String[] args) {
		main("FloodIt");
	}

	public void settings() {
		size(w*s + 2*b, h*s + 3*b + c);
	}

	public void setup() {
		FloodGrid.p = this;
		grid = new FloodGrid(colors);
		noLoop();
		background(40);
	}

	public void draw() {
		grid.draw();
	}

	public void mousePressed() {
		print(mouseX+", "+mouseY);
		
		if (mouseX >= b && mouseX < w*s+b &&
			mouseY >= b && mouseY < h*s+b) {
			grid.click((mouseX-b) / s, (mouseY-b) / s);
			redraw();
			print((mouseX-b) / s + ", " + (mouseY-b) / s);
		}
	}
	
	public void keyPressed() {
		if (key == ' ') {
			grid.reset();
			redraw();
		}
	}

}
