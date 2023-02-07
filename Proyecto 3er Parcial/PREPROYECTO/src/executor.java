
public class executor implements Runnable {
 
    // member variable of this class
    miAlgoritmo algo;
 
    // Constructor of this class
    public executor(int array[], int start, int end, int searchElement)
    {
            this.algo= new miAlgoritmo(array,start, end, searchElement);
                
    }
 
    // Method
    // @Override
    public  void run()
    {
        
       System.out.println(algo.searchElement+ " encontrado " + algo.miAlgoritmo() + " veces ");
        
    }
}