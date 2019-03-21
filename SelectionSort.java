import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import processing.core.PApplet;

public class SelectionSort {

	PApplet p;
	float w, h;
	final int MAX;
	int N;

	Stack<Pair> red;
	Stack<Pair> white;
	ArrayList<Float> data;

	int i, j;
	boolean sorted;

	public SelectionSort(PApplet p, int N) {
		this.p = p;
		w = N;
		h = p.height;
		MAX = N-1;
		reset();
	}

	public void reset() {
		// reset fields
		red = new Stack<>();
		white = new Stack<>();
		data = new ArrayList<>();
		i = 0; j = MAX;
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
	}

	public void step() {
		if (sorted) {
			return;
		}

		if (data.get(j-1) > data.get(j)) {
			swap(j, j-1);
		}
		
		push(red, j);
		push(red, j-1);

		j--;

		if (j == i) {
			i++;
			j = MAX;
		}

		if (i == MAX) {
			sorted = true;
		}
	}

	private void swap(int i, int j) {
		float f1 = data.get(i);
		float f2 = data.get(j);
		data.set(i, f2);
		data.set(j, f1);
	}

	private void push(Stack<Pair> stack, int i) {
		stack.push(new Pair(i, data.get(i)));
	}
}
