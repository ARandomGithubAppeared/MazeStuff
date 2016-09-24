import java.util.Scanner;

/**
 * Created by aaron on 9/20/16.
 */
public class MazeSolver {
    public static MazeDisplay myWindow;

    public static void findPath(Maze theMaze) {
		Coordinate startPos = theMaze.getStart();
		Coordinate finishPos = theMaze.getFinish();
		Coordinate current = startPos;
		ArrayStack stack= new ArrayStack();
		theMaze.visitPos(startPos);
		while (current.equals(finishPos)){
			if (theMaze.movePossible(current,theMaze.northOf(current))){
				current=theMaze.northOf(current);
				theMaze.visitPos(current);
				stack.push(current);
			}else if(theMaze.movePossible(current,theMaze.southOf(current))){
				current=theMaze.southOf(current);
				theMaze.visitPos(current);
				stack.push(current);
			}else if(theMaze.movePossible(current,theMaze.eastOf(current))){
				current=theMaze.eastOf(current);
				theMaze.visitPos(current);
				stack.push(current);
			}else if(theMaze.movePossible(current,theMaze.westOf(current))){
				current=theMaze.westOf(current);
				theMaze.visitPos(current);
				stack.push(current);
			}else{
				theMaze.abandonPos(current);
				current=(Coordinate)stack.pop();
				theMaze.abandonPos(current);
			}
			myWindow.update(current);
			
		}
		

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
                Thread.sleep(50000);
            } catch (Exception ex) {
            }
            myWindow.destroyMaze(); 
        }
    }
}