/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public class miAlgoritmo {
        // Global variables
    int array[];
    int start, end;
    int searchElement;

    public miAlgoritmo(int[] array, int start, int end, int searchElement) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }
    
    public int miAlgoritmo()
    {
 
        // Initially count os set to zero
        int count = 0;
 
        // iterating using for loop
        for (int i = start; i <= end; i++) {
 
            // if element is present in array
            if (array[i] == searchElement) {
 
                // Increment the count
                count++;
            }
        }
 
        // Returning the count of searched element
        return count;
    }
}
    