import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class KrakenSweep extends PApplet {

	String[] paths;
	Grid g;
	int i = 0;
	
	public static void main(String[] args) {
		main("KrakenSweep");
	}
	
	public void settings() {
		int w = 10;
		int h = 10;
		int s = 50;
		
		size(h*s, w*s);
		g = new Grid(w, h, s);
		paths = Kraken.krakenString(w, h);
		
		// shuffling list of paths
		// copy elements into arraylist
		ArrayList<String> arr = new ArrayList<String>();
		for(String p : paths) {
			arr.add(p);
		}

		// shuffle list
		Collections.shuffle(arr);
		
		// copy elements back
		for(int i = 0; i < arr.size(); i++) {
			paths[i] = arr.get(i);
		}
	}
	
	public void setup() {
		GridBox.p = this;
		Grid.p = this;
		stroke(3);
		frameRate(60);
	}
	
	public void draw() {
		frameRate(5);
		
		i = i >= paths.length-1 ? i = 0 : i + 1;
		
		// display grid
		stroke(3);
		strokeWeight(2);
		g.display();

		// draw path
		stroke(10);
		strokeWeight(20);
		g.drawPath(paths[i]);
	}

}
