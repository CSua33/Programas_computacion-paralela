package METODOS_EJECUCION;
import LABERINTO.*;
import java.util.concurrent.Callable;

public class executor implements Callable<String[][]> {
 
    // member variable of this class
    Maze maze;
    int row,col,ini1,ini2,fin1,fin2;
    private String[][] laberinto;
    // Constructor of this class
    public executor(int row, int col, int ini1, int ini2, int fin1, int fin2) {
        this.row = row;
        this.col = col;
        this.ini1 = ini1;
        this.ini2 = ini2;
        this.fin1 = fin1;
        this.fin2 = fin2;
        this.laberinto=new String[row][col];
    }
    
    // Method
    // @Override
    public  String[][] call()
    {
        
               try{
            
            maze=new Maze(row,col,ini1,ini2,fin1,fin2);
            System.out.println("The initial maze is:");
            maze.printMaze();
            laberinto=maze.printMaze();
            if(maze.searchPathQueue()){
                System.out.println("The path found using a queue is:");
                maze.printMaze();
                //laberinto=maze.printMaze();
                System.out.print("Path from start to finish: ");
                maze.printPath();
            }else{
                System.out.println("No path could be found using a queue");
            }
            maze.resetMaze();
        }catch(Exception e){
            System.out.println(e);
        }
        return laberinto;
    }

}