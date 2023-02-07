package LABERINTO;

import java.io.IOException;
import java.util.Scanner;

//This enum describes de valid Square Types and returns the appropriate String
enum SquareType{
    START{
        public String toString() {
            return "S";
        }
    },
    FINISH{
        public String toString() {
            return "F";
        }
    },
    PATH{
        public String toString() {
            return ".";
        }
    },
    WALL{
        public String toString() {
            return "#";
        }
    };}

public class PathMaze {
    
    //The main code reads the document name and tries to create a Maze object, find and print the path using queue and stack
    public static void main(String[] args) throws IOException {
       
    }
    
}
