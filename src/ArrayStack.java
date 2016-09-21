

public class ArrayStack<E> implements Stack{
	private E[] stack;
	private int top;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayStack(int size){
		top = -1;
		this.size = size;
		stack= (E[]) new Object[size];
	}
	public ArrayStack (){
		this(100);
	}
	
	public void test(){
		stack[10].toString();
	}
	
	public E pop() {
		if (top == size -1 ){
			return null;
		}else{
			return stack[top--];
		}
	}

	
	public E top() {
		if (top == size -1 ){
			return null;
		}else{
			return stack[top];
		}
	}

	
	public int size() {
	
		return size;
	}

	@SuppressWarnings("unchecked")
	public void push(Object elt) {
		top++;
		try{	
			stack[top]=(E) elt;
		}catch(ArrayIndexOutOfBoundsException| NullPointerException e){
			
			size=size+100;
			E[] data = (E[]) new Object[size];
			
			for (int x=0;x<size-100;x++){
				data[x]=stack[x];
			}
			stack= data;
			top--;
			this.push(elt);
		}
			
		
		
	}

	public void showAll(){
		for(int x=0;x<=top;x++){
			System.out.println(stack[x].toString());
		}
	}
	
}
