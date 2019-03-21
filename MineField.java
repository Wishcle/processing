import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import processing.core.PApplet;

public class MineField {

	public static PApplet p;
	final int s = 32;
	int w, h, mines;

	public MineCell[][] cells;
	
	boolean xray;

	public MineField(int w, int h, int mines) {
		this.w = w;
		this.h = h;
		this.mines = mines;
		reset();
	}

	public void reset() {
		List<MineCell> cellList = new ArrayList<>();
		cells = new MineCell[h][w];

		// create cells
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				MineCell cell = new MineCell(x, y);
				cells[y][x] = cell;
				cellList.add(cell);
			}
		}

		// select cells to mine up
		for (int i = 0; i < mines; i++) {
			MineCell mine = cellList.get((int) (cellList.size() * Math.random()));
			mine.setMine();
			cellList.remove(mine);
		}

		// calculate neighbors for each MineCell
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				cells[y][x].neighbors = getNeighbors(x, y);
				cells[y][x].calcMineCount();
			}
		}
	}

	public Set<MineCell> getNeighbors(int x, int y) {
		Set<MineCell> set = new HashSet<>();
		try { set.add(cells[y-1][x-1]); } catch (IndexOutOfBoundsException e) { set.add(cells[h-1][w-1]); }
		try { set.add(cells[y-1][ x ]); } catch (IndexOutOfBoundsException e) { set.add(cells[h-1][ x ]); }
		try { set.add(cells[y-1][x+1]); } catch (IndexOutOfBoundsException e) { set.add(cells[h-1][ 0 ]); }
		try { set.add(cells[ y ][x-1]); } catch (IndexOutOfBoundsException e) { set.add(cells[ y ][w-1]); }
		try { set.add(cells[ y ][x+1]); } catch (IndexOutOfBoundsException e) { set.add(cells[ y ][ 0 ]); }
		try { set.add(cells[y+1][x-1]); } catch (IndexOutOfBoundsException e) { set.add(cells[ 0 ][w-1]); }
		try { set.add(cells[y+1][ x ]); } catch (IndexOutOfBoundsException e) { set.add(cells[ 0 ][ x ]); }
		try { set.add(cells[y+1][x+1]); } catch (IndexOutOfBoundsException e) { set.add(cells[ 0 ][ 0 ]); }
		return set;
	}

	public MineCell getCellAt(int x, int y) {
		return cells[y][x];
	}

	public void draw() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				cells[y][x].draw();
			}
		}
	}

	class MineCell {

		// TODO MAKE A NEIGHBORING SET!!!

		final short x, y;
		short mineCount;

		boolean covered;
		boolean hasMine;
		boolean flagged;

		Set<MineCell> neighbors;

		public MineCell(int x, int y) {
			this.x = (short) x;
			this.y = (short) y;
			covered = true;
			neighbors = new HashSet<>();
		}

		public void uncover(boolean override) {
			if (flagged) {
				return;
			}

			if (covered) {
				covered = false;
				if (mineCount == 0) {
					for (MineCell n : neighbors) {
						n.uncover(false);
					}
				}
			} else if (override) {
				for (MineCell n : neighbors) {
					n.uncover(false);
				}
			}
		}

		public int surroundingFlagCount() {
			int flags = 0;
			for (MineCell n : neighbors) {
				if (n.flagged) {
					flags++;
				}
			}
			return flags;
		}

		public Action leftClick() {
			if (covered && flagged) {
				return Action.NONE;
			}
			
			if (hasMine) {
				return Action.EXPLODED;
			}

			if (covered) {
				if (flagged) {
					return Action.NONE;
				} else {
					uncover(false);
					return Action.UNCOVERED;
				}
			} else {
				if (surroundingFlagCount() == mineCount) {
					uncover(true);
				}
			}

			return Action.NONE;
		}

		public Action rightClick() {
			if (covered) {
				flagToggle();
				return Action.FLAG_TOGGLED;
			}

			return Action.NONE;
		}

		public void flagToggle() {
			flagged = !flagged;
		}

		public void setMine() {
			hasMine = true;
		}

		public void calcMineCount() {
			mineCount = 0;
			neighbors.forEach(n -> {
				if (n.hasMine) {
					mineCount++;
				}
			});
		}

		public void draw() {
			if (covered) {
				p.noStroke();
				p.fill((hasMine && xray) ? 80 : 150);
				p.rect(x*s, y*s, s, s);
				p.stroke(20);
				p.noFill();
				p.rect(x*s, y*s, s, s);

				if (flagged) {
					p.noStroke();
					p.fill(255, 0, 0);
					p.ellipse(x*s+(s/2), y*s+(s/2), s/2, s/2);
				}
			} else {
				p.noStroke();
				p.fill(255);
				p.rect(x*s, y*s, s, s);
				p.stroke(20);
				p.noFill();
				p.rect(x*s, y*s, s, s);
				p.fill(0);
				if (mineCount != 0) 
					p.text(mineCount, x*s+5, y*s+12);
			}
		}

		@Override
		public String toString() {
			return "Cell: ("+x+", "+y+")";
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

	}

	enum Action {
		NONE,
		EXPLODED,
		UNCOVERED,
		FLAG_TOGGLED;
	}

}
