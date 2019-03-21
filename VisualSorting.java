import processing.core.PApplet;

public class VisualSorting extends PApplet {

	SelectionSort sort;
	//MergeSort sort;
	
	float N = 100;
	
	public static void main(String[] args) {
		main("VisualSorting");
	}
	
	public void settings() {
		size(800, 400, FX2D);
	}
	
	public void setup() {
		frameRate(120);
		background(50);
		sort = new SelectionSort(this, (int) N);
	}
	
	public void draw() {
		//draw white buffer
		while (!sort.white.isEmpty()) {
			Pair p = sort.white.pop();
			noStroke();
			fill(50);
			rect(p.i * (width/N), 0, width/N, height);
			stroke(0);
			fill(255);
			rect(p.i * (width/N), height-p.f, width/N, height);
		}
		
		// draw red buffer
		while (!sort.red.isEmpty()) {
			Pair p = sort.red.pop();
			noStroke();
			fill(50);
			rect(p.i * (width/N), 0, width/N, height);
			stroke(0);
			fill(255, 0, 0);
			rect(p.i * (width/N), height-p.f, width/N, height);
			sort.white.push(p);
		}
		
		if (!sort.sorted) {
			sort.step();
		}
	}
	
	public void keyPressed() {
		switch(key) {
			case ' ':
				sort.reset();
				break;
		}
	}
}


