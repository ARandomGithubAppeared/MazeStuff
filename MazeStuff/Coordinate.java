package MazeStuff;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Coordinate(){
		this(0,0);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return "X:" + x +" Y:" + y;
	}
	
}
