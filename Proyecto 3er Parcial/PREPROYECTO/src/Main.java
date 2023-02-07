
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.Calendar;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public class Main {
// Custom input array elements
    private static final Random numAleatorio = new Random();
        
    public static void main(String[] args) {
     
        int array[] = new int[100000000];
        int searchElement = 6;
        int start = 0;
        int end = array.length - 1;
        long a;
        long b;
        for (int x=0;x<array.length;x++){
        array[x] = (int) (Math.random()*1000)+1;
        }
        //////////////////// SECUENCIAL ////////////////////////////////////////
        System.out.println("SECUENCIAL");
        
        a=System.currentTimeMillis();
        secuencial secuencial = new secuencial(array, start, end, searchElement);
        secuencial.proceso();
        b=System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (b-a));
        //////////////////// FORK/JOIN /////////////////////////////////////////
         System.out.println("FORK/JOIN");
        // Creating object of ForkJoinPool class
        ForkJoinPool pool = ForkJoinPool.commonPool();
 
        // Now creating object of above class
        forkJoin task = new forkJoin(array, start, end,searchElement);
         a=System.currentTimeMillis();
        int result = pool.invoke(task);
        b=System.currentTimeMillis();
        System.out.println(searchElement + " encontrado " + result + " veces ");
        System.out.println("Tiempo de ejecucion: " + (b-a));
        
        //////////////////// EXECUTE   /////////////////////////////////////////
        System.out.println("EXECUTE");
        ExecutorService es = Executors.newFixedThreadPool(5);
        executor executor = new executor (array, start, end, searchElement);
        a=System.currentTimeMillis();
        es.execute(executor);
        es.shutdown();
        b=System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (b-a));
    }
    
    
}
