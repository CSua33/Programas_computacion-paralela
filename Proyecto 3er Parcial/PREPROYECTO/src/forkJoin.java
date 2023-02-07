
 
// Importing required libraries
import java.io.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
 
// Class 1
// helper class
class forkJoin extends RecursiveTask<Integer> {
 miAlgoritmo algo;
 
    // Constructor for initialising globals
    public forkJoin(int array[], int start, int end, int searchElement)
    {
            this.algo= new miAlgoritmo(array,start, end, searchElement);
                
    }
 
    // Method
    // @Override
    protected Integer compute()
    {
        // Returns the count computed by processSearch
        return algo.miAlgoritmo();
    }
 
    // Method
    // To count the the count of searched element

}