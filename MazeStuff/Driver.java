

public class Driver {
	public static void main(String[] args){
		ArrayStack stack = new ArrayStack();
		for (int x=0;x<200;x++){
			stack.push(new Coordinate(x,x));
		}
		stack.showAll();
	}
}
