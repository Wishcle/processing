import processing.core.PApplet;

public class FlowField extends PApplet {

	int[] flowField;
	
	float xoff, yoff, zoff;
	float inc;
	
	int scl = 10;
	
	public static void main(String[] args) {
		main("FlowField");
	}
	
	public void settings() {
		size(500, 500, FX2D);
		pixelDensity(1);
		//noLoop();
		noiseDetail(10);
		
	}
	
	public void setup() {
		inc = 0.01f;
		noStroke();
		flowField = new int[scl*scl];
	}
	
	public void draw() {
		background(200);
		
		loadPixels();
		//yoff = 0;
		for(int y = 0; y < height; y++) {
			//xoff = 0;
			for(int x = 0; x < width; x++) {
				int i = x + y * width;
				float g = noise(xoff, yoff, zoff) * 255 - 50;
				pixels[i] = color(g);				
				xoff += inc;
			}
			yoff += inc;
		}
		zoff += 0.01;
		updatePixels();
		
//		stroke(255);
//		for(int y = 0; y < height; y += scl) {
//			for(int x = 0; x < width; x += scl) {
//				int i = x + y * width;
//				stroke(255);
//				noFill();
//				rect(x, y, scl, scl);
//				
//			}
//		}
//		noStroke();
		
	}

}
