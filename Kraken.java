
public class Kraken {
	
	private Kraken() {
		
	}

	// returns the number of paths in a specified box
	public static int krakenCount(int rows, int cols) {
		
		if(rows == 1 || cols == 1) {			
			return 1;
		}
		
		int right = krakenCount(rows, cols-1);
		int diag  = krakenCount(rows-1, cols-1);
		int down  = krakenCount(rows-1, cols);

		return right + diag + down;
	}
	
	// returns a String array of all the paths in a specified box
	// "R" = right
	// "G" = diagonal
	// "D" = down
	// ex. "RGDGRRD"
	public static String[] krakenString(int rows, int cols) {
		String[] paths;
		
		
		if(cols == 1 && rows == 1) {
			paths = new String[]{""};
			return paths;
		}
		
		if(cols == 1) {
			paths = krakenString(rows-1, cols);
			
			for(int i = 0; i < paths.length; i++) {
				paths[i] = "D" + paths[i];
			}
			
			return paths;			
		}
		
		if(rows == 1) {
			paths = krakenString(rows, cols-1);
			
			for(int i = 0; i < paths.length; i++) {
				paths[i] = "R" + paths[i];
			}
			
			return paths;			
		}
		
		String[] right = krakenString(rows, cols-1);
		String[] diag  = krakenString(rows-1, cols-1);
		String[] down  = krakenString(rows-1, cols);
		
		int l = right.length + diag.length + down.length;
		paths = new String[l];
		int c = 0;
		
		for(int i = 0; i < right.length; i++) {
			paths[c] = "R" + right[i];
			c++;
		}
		
		for(int i = 0; i < diag.length; i++) {
			paths[c] = "G" + diag[i];
			c++;
		}
		
		for(int i = 0; i < down.length; i++) {
			paths[c] = "D" + down[i];
			c++;
		}
		
		return paths;
	}

}