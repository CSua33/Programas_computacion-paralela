package LABERINTO;

class Node{
    Position position;
    Node next;
    
    public Node(Position position){
        this.position=position;
        this.next=null;
    }
}

public class Queue {
    Node front, rear;
    
    //The constructor initializes the rear and front Positions of the list as null
    public Queue(){
        front=rear=null;
    }
    
    //This method Enqueue a Position in the queue list
    public void Enqueue(Position position){
        Node temp=new Node(position);
        if(rear==null){
            front=rear=temp;
            return;
        }
        
        rear.next=temp;
        rear=temp;
    }
    
    //This method Dequeue a Position from the queue list if it's not empty
    public void Dequeue(){
        if(front!=null){
            front=front.next;
            
            if(front==null){
                rear=null;
            }
        }
    }
    
    //This method returns the Front Position of the queue list
    public Position Front(){
        return front.position;
    }
    
    //This method returns true if the queue list is empty
    public boolean isEmpty(){
        if(front==null){
            return true;
        }else{
            return false;
        }
    }
    
    //This method prints the coordinate values of every Position in the queue list
    public void printQueue(){
        Node temp=front;
        while(temp!=null){
            System.out.print(temp.position.getCoordinates()+" ");
            temp=temp.next;
        }
        System.out.println("");
    }
}
