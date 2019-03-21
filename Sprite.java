import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import processing.core.PApplet;

public class Sprite {

	PApplet p;

	int w; // width
	int h; // height
	int c; // # of hex columns

	Set<Layer> layers;

	public Sprite(int w, int h, PApplet p) {
		this.w = w;
		this.h = h;
		this.p = p;
		c = (int) Math.ceil(w / 4.);

		layers = new HashSet<>();
	}

	public void addLayer(int color, String[] hex) {
		boolean[][] pixels = convertHexToArray(hex);
		layers.add(new Layer(color, pixels));		
	}
	
	public void updateLayer(int oldColor, int newColor) {
		for (Layer l : layers) {
			if (l.color == oldColor) {
				l.color = newColor;
			}
		}
	}

	private boolean[][] convertHexToArray(String[] hex) {
		if (!isValidHex(hex)) {
			throw new IllegalArgumentException("-- INVALID HEX --");
		}

		boolean[][] pixels = new boolean[h][w];
		int ph = 0;
		int pw = 0;

		int dec; // decimal rep. of hex
		for (int i = 0; i < c; i++) {	
			for (char c : hex[i].toCharArray()) {
				dec = hexToInt(c);
				for (int j = 0; j < 4; j++) {
					if(dec >= Math.pow(2, 3-j)) {
						if (ph < h && pw+j < w) {
							dec -= Math.pow(2, 3-j);
							pixels[ph][pw+j] = true;
						}
					}
				}	
				ph++;
			}
			ph = 0;
			pw += 4;
		}

		// prints the sprite
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (pixels[i][j]) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.println();
		}

		return pixels;
	}

	private int hexToInt(char c) {
		if (48 <= c && c <= 57) {
			return c - 48;
		} else if (65 <= c && c <= 70) {
			return c - 55;
		} else {
			return 0;
		}
	}

	private boolean isValidHex(String[] hex) {
		if (hex == null || hex.length != c) {
			return false;
		}

		for (int i = 0; i < hex.length; i++) {
			if (hex[i].length() != h) {
				return false;
			}
		}

		return true;
	}

	public void draw(int x, int y, float s) {
		for (Layer l : layers) {
			l.draw(x, y, s);
		}
	}

	class Layer {
		int color;
		boolean[][] pixels;

		public Layer(int color, boolean[][] pixels) {
			this.color = color;
			this.pixels = pixels;
		}

		public void draw(float x, float y, float s) {
			p.noStroke();
			p.fill(color);

			for (int j = 0; j < w; j++) {
				for (int i = 0; i < h; i++) {
					if (pixels[j][i])
						p.rect(x + i*s, y + j*s, s, s);
				}
			}
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(color, pixels);
		}
	}

}
