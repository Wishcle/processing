import java.util.LinkedList;

import processing.core.PApplet;

public class DragonCurve extends PApplet {

	LinkedList<Stick> sticks;
	int lagCount = 0;
	
	public static void main(String[] args) {
		main("DragonCurve");
	}
	
	public void settings() {
		size(1200, 800, FX2D);
	}
	
	public void setup() {
		noLoop();
		Stick.p = this;
		sticks = new LinkedList<>();
		sticks.add(new Stick(350, 500, 950, 500, false));
	}
	
	public void draw() {
		background(200);
		
		for (Stick s : sticks) {
			s.draw();
		}
		
		//saveFrame("dragon_curve_##.png");
	}
	
	public void splitList() {
		boolean left = true;
		LinkedList<Stick> nextList = new LinkedList<>();
		for (Stick s : sticks) {
			Stick.Pair p = s.split(left);
			nextList.add(p.s1);
			nextList.add(p.s2);
			left = !left;
		}
		
		sticks = nextList;
	}
	
	public void keyPressed() {
		if (key == ' ') {
			lagCount++;
			
			if (lagCount > 18) {
				lagCount = 0;
				setup();
			} else {
				splitList();
			}
			
			redraw();			
		} else if (key == 'r' || key == 'R') {
			setup();
			redraw();
		}
	}

}
