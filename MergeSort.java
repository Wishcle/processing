import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

import processing.core.PApplet;

public class MergeSort {

	PApplet p;
	float w, h;
	final int MAX;
	
	Stack<Pair> red;
	Stack<Pair> white;
	
	ArrayList<Float> data;
	Stack<Range> subarrays;
	
	int rt, lt, x;
	float[] list;
	Mode mode;
	
	public MergeSort(PApplet p) {
		this.p = p;
		w = p.width;
		h = p.height;
		MAX = (int) w-1;
		reset();
	}
	
	public void reset() {
		// reset fields
		red = new Stack<>();
		white = new Stack<>();
		data = new ArrayList<>();
		mode = Mode.SORT;
		
		// populate data list
		for (int i = 0; i < w; i++) {
			data.add(i*(h/w));
		}
		
		// shuffle data list, add to white buffer
		Collections.shuffle(data);
		for (int i = 0; i < w; i++) {
			push(white, i);
		}
	}
	
	public void step() {
		// TODO		
	}
	
	public void sort() {
		sort(0, MAX);
	}
	
	private void sort(int i, int j) {
		if (i == j) {
			return;
		}
		
		// divide array by two,
		// call sort on both halves
		int m = (j+i)/2;
		sort(i, m);
		sort(m+1, j);
		
		// combine the sorted halves
		// into one sorted array
		lt = i;
		rt = m+1;
		float[] list = new float[j-i+1];
		for (int x = 0; x <= list.length; x++) {
			if (lt == m) {
				push(red, rt);
				rt++;
			} else if (rt == list.length) {
				
				// TODO ...
				
				
				
			}
		}
		
		p.draw();
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
	
	private enum Mode {
		SORT, COPY, DONE;
	}
}


