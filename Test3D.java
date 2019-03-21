import processing.core.PApplet;

public class Test3D extends PApplet {

	float x, y, z;
	float inc = 0.01f;
	
	public static void main(String[] args) {
		main("Test3D");
	}
	
	public void settings() {
		size(600, 500, P3D);
	}
	
	public void setup() {
		background(20);
		lights();
		fill(200, 50, 50);
		noStroke();
		x = 0;
		y = 10000;
		z = 20000;
	}
	
	public void draw() {
		background(20);
		//noStroke();
		lights();
		float sx = map(noise(x), 0, 1, 0, width);
		float sy = map(noise(y), 0, 1, 0, height);
		float sz = map(noise(z), 0, 1, -200, 200);
		translate(sx, sy, sz);
		sphere(50);
		
		x += inc;
		y += inc;
		z += inc;
	}

}
