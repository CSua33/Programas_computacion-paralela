/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */

public class secuencial  {
 
    // member variable of this class
    miAlgoritmo algo;
 
    // Constructor of this class
    public secuencial(int array[], int start, int end, int searchElement)
    {
            this.algo= new miAlgoritmo(array,start, end, searchElement);
                
    }
 
    // Method
    // @Override
    public  void proceso()
    {
       System.out.println(algo.searchElement+ " encontrado " + algo.miAlgoritmo() + " veces ");
        
    }
}