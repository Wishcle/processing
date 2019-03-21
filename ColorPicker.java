import processing.core.PApplet;
import processing.event.MouseEvent;

public class ColorPicker extends PApplet {

	Color mode;
	float r, g, b;

	public static void main(String[] args) {
		main("ColorPicker");
	}

	public void settings() {
		size(1000, 600, FX2D);
		pixelDensity(1);
	}

	public void setup() {
		mode = Color.RED;
		r = 255;
		surface.setResizable(false);
	}

	public void draw() {
		loadPixels();
		for(float y = 0; y < height; y++) {
			for(float x = 0; x < width; x++) {
				float xr = map(x/width, 0, 1, 255, r);
				float xg = map(x/width, 0, 1, 255, g);
				float xb = map(x/width, 0, 1, 255, b);
				float yr = map(y/height, 0, 1, xr, 0);
				float yg = map(y/height, 0, 1, xg, 0);
				float yb = map(y/height, 0, 1, xb, 0);
				pixels[(int) (x + y * width)] = color(yr, yg, yb);
			}
		}
		updatePixels();

		int c = get(mouseX, mouseY);
		fill(c);
		ellipse(mouseX, mouseY, 100, 100);
		
		fill(255-red(c), 255-green(c), 255-blue(c));
		String rgb = 
			(int) red(c) + ", " + 
			(int) green(c) + ", " + 
			(int) blue(c);
		text(rgb, mouseX-35, mouseY+65);
	}

	public void mouseClicked() {
		int c = get(mouseX, mouseY);
		int red = (int) red(c);
		int green = (int) green(c);
		int blue = (int) blue(c);
		System.out.println(red+", "+green+", "+blue);
	}

	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		e *= 4;
		switch(mode) {
		case BLUE:
			b = 255;
			if(r >= 0 && g == 0) {
				r += e;
				if(r > 255) {
					b = 255 - (r - 255);
					r = 255;
					mode = Color.RED;
				} else if(r < 0) {
					g = -r;
					r = 0;
				}
			} else if(g >= 0 && r == 0) {
				g -= e;
				if(g > 255) {
					b = 255 - (g - 255);
					g = 255;
					mode = Color.GREEN;
				} else if(g < 0) {
					r = -g;
					g = 0;
				}
			}
			break;
		case GREEN:
			g = 255;
			if(b >= 0 && r == 0) {
				b += e;
				if(b > 255) {
					g = 255 - (b - 255);
					b = 255;
					mode = Color.BLUE;
				} else if(b < 0) {
					r = -b;
					b = 0;
				}
			} else if(r >= 0 && b == 0) {
				r -= e;
				if(r > 255) {
					g = 255 - (r - 255);
					r = 255;
					mode = Color.RED;
				} else if(r < 0) {
					b = -r;
					r = 0;
				}
			}
			break;
		case RED:
			r = 255;
			if(g >= 0 && b == 0) {
				g += e;
				if(g > 255) {
					r = 255 - (g - 255);
					g = 255;
					mode = Color.GREEN;
				} else if(g < 0) {
					b = -g;
					g = 0;
				}
			} else if(b >= 0 && g == 0) {
				b -= e;
				if(b > 255) {
					r = 255 - (b - 255);
					b = 255;
					mode = Color.BLUE;
				} else if(b < 0) {
					g = -b;
					b = 0;
				}
			}
			break;
		default:
			break;
		}
	}

}

enum Color {
	RED, GREEN, BLUE;
}

