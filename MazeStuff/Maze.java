
/**
 * Created by aaron on 9/20/16.
 */

import java.util.Scanner;

import javax.xml.stream.events.EndDocument;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
	private MazeSquare[][] square;
	private Coordinate startPos;
	private Coordinate finishPos;
	private int numRows;
	private int numCols;

	public Maze(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		square = new MazeSquare[numRows][numCols];
		boolean[] defaultWallSet = { true, true, true, true };
		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				square[x][y] = new MazeSquare(new Coordinate(x, y), defaultWallSet);
			}
		}
		genMaze();
		clear();
	}

	public MazeSquare squareAt(Coordinate p) {
		return square[p.getRow()][p.getCol()];
	}

	public void visitPos(Coordinate p) {
		square[p.getRow()][p.getCol()].visit();
	}

	public void abandonPos(Coordinate p) {
		square[p.getRow()][p.getCol()].abandon();
	}

	public Coordinate getStart() {
		return startPos;
	}

	public Coordinate getFinish() {
		return finishPos;
	}

	public Coordinate northOf(Coordinate p) {
		return new Coordinate(p.getRow()-1, p.getCol());
	}

	public Coordinate eastOf(Coordinate p) {
		return new Coordinate(p.getRow(), p.getCol()+1);
	}

	public Coordinate southOf(Coordinate p) {
		return new Coordinate(p.getRow()+1, p.getCol());
	}

	public Coordinate westOf(Coordinate p) {
		return new Coordinate(p.getRow(), p.getCol()-1);
	}

	public boolean movePossible(Coordinate from, Coordinate to) {
		if (validPos(to) && validPos(from)) {
			if (squareAt(to).isVisited() || squareAt(to).isAbandoned()) {
				return false;
			} else {
				if (to.equals(northOf(from))) {
					if (squareAt(to).getWall(Direction.SOUTH) || squareAt(from).getWall(Direction.NORTH)) {
						return false;
					}
				}
				if (to.equals(southOf(from))) {
					if (squareAt(to).getWall(Direction.NORTH) || squareAt(from).getWall(Direction.SOUTH)) {
						return false;
					}
				}
				if (to.equals(eastOf(from))) {
					if (squareAt(to).getWall(Direction.WEST) || squareAt(from).getWall(Direction.EAST)) {
						return false;
					}
				}
				if (to.equals(westOf(from))) {
					if (squareAt(to).getWall(Direction.EAST) || squareAt(from).getWall(Direction.WEST)) {
						return false;
					}
				}
			}
		}
		return true;

	}

	private void genMaze() {
		Random rng = new Random();
		startPos = new Coordinate(rng.nextInt(numRows), 0);
		squareAt(startPos).toggleWall(Direction.WEST);
		finishPos = new Coordinate(rng.nextInt(numRows),numCols-1);
		squareAt(finishPos).toggleWall(Direction.EAST);
		Coordinate current = startPos;

		ArrayStack stack = new ArrayStack();

		stack.push(current);
		while (stack.top() != null) {
			// Coordinate test=(Coordinate)stack.top();
			// System.out.println(test.toString());

			visitPos(current);
			ArrayList neighbors = unvisitedNeighbors(current);

			if (!neighbors.isEmpty()) {
				rng = new Random();
				Coordinate next = (Coordinate) neighbors.get(rng.nextInt(neighbors.size()));
			
			//	System.out.println(current.toString());
			//	System.out.println(next.toString());
			//	if (movePossible(current, next)) {
					if (northOf(current).equals(next)) {
						squareAt(current).toggleWall(Direction.NORTH);
						squareAt(next).toggleWall(Direction.SOUTH);
					}
					if (southOf(current).equals(next)) {
						squareAt(current).toggleWall(Direction.SOUTH);
						squareAt(next).toggleWall(Direction.NORTH);
					}
					if (eastOf(current).equals(next)) {
						squareAt(current).toggleWall(Direction.EAST);
						squareAt(next).toggleWall(Direction.WEST);
					}
					if (westOf(current).equals(next)) {
						squareAt(current).toggleWall(Direction.WEST);
						squareAt(next).toggleWall(Direction.EAST);
					}
					current=next;
					stack.push(current);
				
			}
		}

	}

	private void clear() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				square[i][j].clear();
			}
		}
	}

	// I'm going to leave this in here since we haven't
	// discussed the use of the ArrayList collection from the java library
	private ArrayList<Coordinate> unvisitedNeighbors(Coordinate p) {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();

		int r = p.getRow();
		int c = p.getCol();

		if (r > 0 && !square[r - 1][c].isVisited())
			list.add(new Coordinate(r - 1, c));
		if (r < numRows - 1 && !square[r + 1][c].isVisited())
			list.add(new Coordinate(r + 1, c));
		if (c > 0 && !square[r][c - 1].isVisited())
			list.add(new Coordinate(r, c - 1));
		if (c < numCols - 1 && !square[r][c + 1].isVisited())
			list.add(new Coordinate(r, c + 1));

		return list;
	}

	// this will be made use of in the maze solver but is not useful here.
	// Just checks if p is within the bounds of the maze.
	private boolean validPos(Coordinate p) {
		return ((p.getRow() < numRows) && (p.getRow() >= 0) && (p.getCol() < numCols) && (p.getCol() >= 0));
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		// output the top
		for (int i = 0; i < numCols; i++)
			buf.append("__");
		buf.append("_\n");

		// output the rows
		for (int i = 0; i < numRows; i++) {
			if (i != startPos.getRow()) {
				buf.append("|");
			} else {
				buf.append(" ");
			}

			for (int j = 0; j < numCols; j++) {
				if (square[i][j].getWall(Direction.SOUTH)) {
					buf.append("_");
				} else {
					buf.append(" ");
				}

				if (square[i][j].getWall(Direction.EAST)) {
					buf.append("|");
				} else {
					if (j + 1 < numCols) {
						if (square[i][j + 1].getWall(Direction.SOUTH)) {
							buf.append("_");
						} else {
							buf.append(".");
						}
					}
				}
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Scanner fromUser = new Scanner(System.in);
		System.out.print("rows? ");
		int r = fromUser.nextInt();
		System.out.print("cols? ");
		int c = fromUser.nextInt();

		Maze aMaze = new Maze(r, c);
		System.out.println(aMaze);
	}
}
