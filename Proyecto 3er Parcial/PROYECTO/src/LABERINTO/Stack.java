package LABERINTO;

public class Stack {
    public Position[] stack;
    private int size;
    private int index;
    
    //The constructor creates an array with the approptiate size and sets the index as -1
    Stack(int size){
        index=-1;
        this.size=size;
        stack=new Position[size];
    }
    
    //This method Pushes a Position in the stack array
    public void Push(Position position){
        index++;
        stack[index]=position;
    }
    
    //This method Pops a Position from the stack array if it's not empty
    public void Pop(){
        if(!isEmpty()){
            index--;
        }
    }
    
    //This method returns the Top Position of the stak array
    public Position Top(){
        return stack[index];
    }
    
    //This method returns true if the index equals -1 or false if it's higher
    public boolean isEmpty(){
        if(index==-1){
            return true;
        }else{
            return false;
        }
    }
    
    //This method prints the coordinate values of every Position in the stack array
    public void printStack(){
        for(int i=0;i<=index;i++){
            System.out.print(stack[i].getCoordinates()+" ");
        }
        System.out.println("");
    }
}
