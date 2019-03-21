import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

import processing.core.PApplet;

public class QuickSort {

	PApplet p;
	float w, h;
	final int MAX;
	int N;
	
	Stack<Pair> red;
	Stack<Pair> white;
	ArrayList<Float> data;
	
	Stack<Range> ranges;
	boolean sorted;
	int part;
	
	public QuickSort(PApplet p, int N) {
		this.p = p;
		w = N;
		h = p.height;
		MAX = N-1;
	}
	
	public void reset() {
		// reset fields
		red = new Stack<>();
		white = new Stack<>();
		data = new ArrayList<>();
		sorted = false;
		
		// populate data list
		for (int i = 0; i < w; i++) {
			data.add(i*(h/w));
		}
		
		// shuffle data list, add to white buffer
		Collections.shuffle(data);
		for (int i = 0; i < w; i++) {
			push(white, i);
		}
		
		ranges.push(new Range(0, MAX));
	}
	
	public void step() {
		
	}
	
	private void push(Stack<Pair> stack, int i) {
		stack.push(new Pair(i, data.get(i)));
	}
	
	private class Range {
		final int i, j;
		
		public Range(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(i, j);
		}
	}
}
