/**
 * Created by aaron on 9/20/16.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private MazeSquare[][] square;
    private Coordinate startPos;
    private Coordinate finishPos;
    private int numRows;
    private int numCols;

    public Maze(int numRows, int numCols) {
        throw new RuntimeException("Unimplemented.");
    }

    public MazeSquare squareAt(Coordinate p) {
    	
    }

    public void visitPos(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public void abandonPos(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public Coordinate getStart() { throw new RuntimeException("Unimplemented."); }

    public Coordinate getFinish() { throw new RuntimeException("Unimplemented."); }

    public Coordinate northOf(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public Coordinate eastOf(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public Coordinate southOf(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public Coordinate westOf(Coordinate p) { throw new RuntimeException("Unimplemented."); }

    public boolean movePossible(Coordinate from, Coordinate to) { throw new RuntimeException("Unimplemented."); }

    private void genMaze() { throw new RuntimeException("Unimplemented."); }

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
