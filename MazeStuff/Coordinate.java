/**
 * Created by aaron on 9/20/16.
 */
public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(Coordinate rhs) {
        return (row == rhs.getRow() && col == rhs.getCol());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public String toString(){
    	return "X: " + getCol() + " Y: " + getRow();
    }

}