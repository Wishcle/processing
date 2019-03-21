import processing.core.PApplet;
import processing.event.MouseEvent;

public class ModTable extends PApplet {

	float mod;
	boolean reverse;

	public static void main(String[] args) {
		main("ModTable");
	}

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		noStroke();
		mod = 1;
		reverse = false;
	}

	public void draw() {
		if(!reverse) {
			mod += 6;
			if(mod > 500) {
				reverse = true;
			}
		} else {
			mod -= 6;
			if(mod < 2) {
				exit();
			}
		}
		
		float t = width / (mod-2);
		for(int y = 1; y < mod; y++) {
			for(int x = 1; x < mod; x++) {
				float product = x * y % mod;
				float gray = map(product, 0, mod-1, 0, 255);
				fill(gray);
				rect((x-1)*t, (y-1)*t, t, t);
			}
		}

		//saveFrame("/Users/Riley/Desktop/GIFs/source/mod-###.png");
	}

	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		mod += 4*e;
		if(mod < 1) {
			mod = 1;
		}
	}
	
	public void mouseClicked() {
		System.out.println(mod);
	}

}
