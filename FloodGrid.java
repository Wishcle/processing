import java.util.Objects;
import java.util.Stack;

import processing.core.PApplet;

public class FloodGrid {

	static PApplet p;
	
	int w = FloodIt.w;	// grid width
	int h = FloodIt.h;	// grid height
	int s = FloodIt.s;	// cube size
	int f = FloodIt.f;	// frame thickness
	int b = FloodIt.b;	// border width
	int c = FloodIt.c;	// color box size
	
	int[][] grid;
	int[] colors;
	
	public FloodGrid(int[] colors) {
		this.colors = colors;
		reset();
	}
	
	public void reset() {
		grid = new int[h][w];
		for (int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int rand = (int) (Math.random() * colors.length);
				grid[y][x] = colors[rand];
			}
		}
	}
	
	public void draw() {
		p.noStroke();
		for (int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int c = grid[y][x];
				p.fill(c);
				p.rect(x*s + b, y*s + b, s, s);
			}
		}
	}
	
	public void click(int i, int j) {
		flood(grid[j][i]);
	}
	
	public void flood(int c) {
		int old = grid[0][0];
		if (old == c) return;
		
		Stack<Point> points = new Stack<>();
		points.add(new Point(0, 0));
		
		while(!points.isEmpty()) {
			Point p = points.pop();
			p.set(c);
			Point up = new Point(p.x, p.y-1);
			Point dn = new Point(p.x, p.y+1);
			Point lt = new Point(p.x-1, p.y);
			Point rt = new Point(p.x+1, p.y);
			if (up.isValid(old)) points.push(up);
			if (dn.isValid(old)) points.push(dn);
			if (lt.isValid(old)) points.push(lt);
			if (rt.isValid(old)) points.push(rt);
		}
	}

	private class Point {
		final int x, y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean isValid(int old) {
			if ((x < w) && (y < h) && (x >= 0) && (y >= 0))
				return color() == old;			
			return false;
		}
		
		public int color() {
			return grid[y][x];
		}
		
		public void set(int c) {
			grid[y][x] = c;
		}
		
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
}
