import java.util.Scanner;

/**
 * Created by aaron on 9/20/16.
 */
public class MazeSolver {
    public static MazeDisplay myWindow;

    public static void findPath(Maze theMaze) {
        // solver algorithm goes here.
        // any time a MazeSquare's state changes
        // your code must call myWindow.update(Coordinate)
        // with the coordinates of the changed square.
    }

    public static void main(String[] args) {
        Scanner fromUser = new Scanner(System.in);
        System.out.print("Number of rows? ");
        int ROWS = fromUser.nextInt();
        System.out.print("Number of cols? ");
        int COLS = fromUser.nextInt();

        for (int i = 0; i < 5; i++) {
            Maze aMaze = new Maze(ROWS, COLS);
            myWindow = new MazeDisplay(aMaze, ROWS, COLS);
            myWindow.showMaze();
            findPath(aMaze);
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
            }
            myWindow.destroyMaze();
        }
    }
}