package LABERINTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    public Position[][] positions;
    private Position[] path;
    private Position start,finish;
    private int rows,columns;
    private Queue queue;
    private Stack stack;
    private String[][] laberinto;
    
    //The constructor gets the document name and reads de maze in the document, creates a 2D array of the appropriate size, and then fill the array.
    public Maze(int row,int col,int ini1,int ini2,int fin1,int fin2) throws FileNotFoundException, IOException{
        
        
        rows=row;
        
        columns=col;

        positions=new Position[rows][columns];
        this.laberinto=new String[rows][columns];
        for(int i1=0;i1<rows;i1++){
            for(int i2=0;i2<columns;i2++){
               positions[i1][i2]=new Position(i1,i2,SquareType.PATH);
            }
        }
        //Obstaculo
        positions[4][2] = new Position(4,2,SquareType.WALL);
        positions[4][3] = new Position(4,3,SquareType.WALL);
        positions[4][4] = new Position(4,4,SquareType.WALL);
        positions[4][5] = new Position(4,5,SquareType.WALL);
        //Inicio
        positions[ini1][ini2]=new Position(ini1,ini2,SquareType.START);
        start=positions[ini1][ini2];
        //Fin
        positions[fin1][fin2]=new Position(fin1,fin2,SquareType.FINISH);
        finish=positions[fin1][fin2];
    }
    
    //This method search the path using Stack and returns true if it's successful or false if there is no path
    public boolean searchPathStack(){
        Position current=start;
        stack=new Stack(rows*columns);
        stack.Push(start);
        stack.Top().isVisited=true;
        
        while(!stack.isEmpty()){
            current=stack.Top();
            stack.Pop();
            if(current==finish){
                break;
            }
            Position[] neighbours=getNeighbours(current); 
            for(int i=0;i<neighbours.length;i++){
                stack.Push(neighbours[i]);
                neighbours[i].isVisited=true;
                neighbours[i].previousPosition=current;
            }
        }
        if(current==finish){
            setPathArray();
            return true;
        }else{
            return false;
        }
    }
    
    //This method searches the path using Queue and returns true if it's successful or false if it doesn't find a path
    public boolean searchPathQueue(){
        Position current=start;
        queue=new Queue();
        queue.Enqueue(start);
        queue.Front().isVisited=true;
        
        while(!queue.isEmpty()){
            current=queue.Front();
            queue.Dequeue();
            if(current==finish){
                break;
            }
            Position[] neighbours=getNeighbours(current); 
            for(int i=0;i<neighbours.length;i++){
                queue.Enqueue(neighbours[i]);
                neighbours[i].isVisited=true;
                neighbours[i].previousPosition=current;
            }
        }
        
        if(current==finish){
            setPathArray();
            return true;
        }else{
            return false;
        }
    }
    
    //This method fills the path array using the previous position of every Position in the path from the finish to the start, and sets as visited only the positions of the path
    private void setPathArray(){
        Position[] temp=new Position[rows*columns];
        temp[0]=finish;
        int amount=1;
        while(temp[amount-1]!=start){
            amount++;
            temp[amount-1]=temp[amount-2].previousPosition;
        }
        path=new Position[amount];
        for(int i=amount-1;i>=0;i--){
            path[amount-(i+1)]=temp[i];
        }
        resetMaze();
        for(int i1=0;i1<rows;i1++){
            for(int i2=0;i2<columns;i2++){
                for(int i3=0;i3<amount;i3++){
                    if(positions[i1][i2]==path[i3]){
                        positions[i1][i2].isVisited=true;
                    }
                }
            }
        }
    }
    
    //This method returns a Position array that contains the valid neighbours of the given Position
    private Position[] getNeighbours(Position position){
        int amount=0;
        Position[] temp=new Position[4];
        if(position.column!=0){
            if((positions[position.row][position.column-1].typeOfSquare==SquareType.PATH||positions[position.row][position.column-1].typeOfSquare==SquareType.FINISH)&&!positions[position.row][position.column-1].isVisited){
                    temp[amount]=positions[position.row][position.column-1];
                    amount++;
            }
        }
        if(position.row!=0){
            if((positions[position.row-1][position.column].typeOfSquare==SquareType.PATH||positions[position.row-1][position.column].typeOfSquare==SquareType.FINISH)&&!positions[position.row-1][position.column].isVisited){
                    temp[amount]=positions[position.row-1][position.column];
                    amount++;
            }
        }
        if(position.column!=columns-1){
            if((positions[position.row][position.column+1].typeOfSquare==SquareType.PATH||positions[position.row][position.column+1].typeOfSquare==SquareType.FINISH)&&!positions[position.row][position.column+1].isVisited){
                    temp[amount]=positions[position.row][position.column+1];
                    amount++;
            }
        }
        if(position.row!=rows-1){
            if((positions[position.row+1][position.column].typeOfSquare==SquareType.PATH||positions[position.row+1][position.column].typeOfSquare==SquareType.FINISH)&&!positions[position.row+1][position.column].isVisited){
                    temp[amount]=positions[position.row+1][position.column];
                    amount++;
            }
        }
        Position[] neighbours=new Position[amount];
        for(int i=0;i<amount;i++){
           neighbours[i]=temp[i];     
        }
        return neighbours;
    }
    
    //This method returns the Start Position
    public Position getStartPosition(){
        return start;
    }
    
    //This method returns the Finish Position
    public Position getFinishPosition(){
        return finish;
    }
    
    //This method resets the maze setting the isVisited field of every Position of the maze in false
    public void resetMaze(){
        for(int i1=0;i1<rows;i1++){
            for(int i2=0;i2<columns;i2++){
                positions[i1][i2].isVisited=false;
            }
        }
    }
    
    //This method prints the maze, printing the symbol of every Position and an "*" if the path has been visited
    public String[][] printMaze(){
        for(int i1=0;i1<rows;i1++){
            for(int i2=0;i2<columns;i2++){
                if(positions[i1][i2].isVisited&&positions[i1][i2].typeOfSquare==SquareType.PATH){
                    System.out.print("*"); 
                    laberinto[i1][i2]="*";
                }else{
                    System.out.print(positions[i1][i2].getSquareType());
                    laberinto[i1][i2]=positions[i1][i2].getSquareType();
                }
            }
            System.out.println("");
        }
        return laberinto;
    }
    
    //This method prints the coordinates of every Position of the path array
    public void printPath(){
        for(int i=0;i<path.length;i++){
            System.out.print(path[i].getCoordinates()+" ");
        }
        System.out.println("");
    }
}