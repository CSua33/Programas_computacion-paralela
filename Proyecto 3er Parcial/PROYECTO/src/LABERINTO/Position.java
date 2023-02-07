package LABERINTO;

public class Position {
    public SquareType typeOfSquare;
    public int row,column;
    public boolean isVisited;
    public Position previousPosition;
    
    //The constructor sets the row, column and the type of the location with the given values the other fields as default values. 
    public Position(int row,int column,SquareType typeOfSquare){
        this.row=row;
        this.column=column;
        this.typeOfSquare=typeOfSquare;
        isVisited=false;
        previousPosition=null;
    }
    
    //This method returns the String represetnation of the Position
    public String getSquareType(){
        return typeOfSquare.toString();
    }
    
    //This methos returns a String with the coordinates of the Position in the format "(row, column)"
    public String getCoordinates(){
        return "("+row+", "+column+")";
    } 
}
