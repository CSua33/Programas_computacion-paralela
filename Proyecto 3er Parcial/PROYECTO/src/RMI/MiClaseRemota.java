package RMI;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import METODOS_EJECUCION.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public class MiClaseRemota extends java.rmi.server.UnicastRemoteObject implements MiInterfazRemota {
    
    String tiempo;
    int filas;
    int columnas;
    int x_puntoS;
    int y_puntoS;
    int x_puntoF;
    int y_puntoF;
    String[][] respLaberinto;
    public MiClaseRemota() throws java.rmi.RemoteException {
// CÃ³digo del constructor
    }

    public  String miMetodo1() throws java.rmi.RemoteException {
        // AquÃ­ ponemos el cÃ³digo que queramos
        //System.out.println("Hola mundo");
        return "Hola mundo";
    }
    
    public  String[][] secuencial() throws java.rmi.RemoteException {
        long a=0;
        long b=0;
        String[][] laberinto=new String[filas][columnas];
        System.out.println("SECUENCIAL");
        //a=System.currentTimeMillis();
        a=System.nanoTime();
        secuencial secuencial = new secuencial(filas,columnas,x_puntoS,y_puntoS,x_puntoF,y_puntoF);
        //secuencial.proceso();
        laberinto=secuencial.proceso();
        //b=System.currentTimeMillis();
        b=System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (b-a));
        tiempo=Long.toString(b-a);
        respLaberinto=laberinto;
        return laberinto;
 
    }
    public  String[][] forkJoin() throws java.rmi.RemoteException {
        long a=0;
        long b=0;
        String[][] laberinto=new String[filas][columnas];
         System.out.println("FORK/JOIN");
        // Creating object of ForkJoinPool class
        ForkJoinPool pool = ForkJoinPool.commonPool();
 
        // Now creating object of above class
        forkJoin task = new forkJoin(filas,columnas,x_puntoS,y_puntoS,x_puntoF,y_puntoF);
        //a=System.currentTimeMillis();
         a=System.nanoTime();
        int result = pool.invoke(task);
        laberinto=task.getLaberinto();
        //b=System.currentTimeMillis();
        b=System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (b-a));
        tiempo=Long.toString(b-a);
        respLaberinto=laberinto;
        return laberinto;
    }
      public  String[][] executor() throws java.rmi.RemoteException {
        long a;
        long b;
        String[][] laberinto=new String[filas][columnas];
        System.out.println("EXECUTE");
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<String[][]> result = es.submit(new executor (filas,columnas,x_puntoS,y_puntoS,x_puntoF,y_puntoF));
        //a=System.currentTimeMillis();
        a=System.nanoTime();
        //es.execute(executor);
        try {
             laberinto = result.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        es.shutdown();
        //b=System.currentTimeMillis();
        b=System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (b-a));
        tiempo=Long.toString(b-a);
        respLaberinto=laberinto;
        return laberinto;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setFilas(int filas) {
        System.out.println("Filas recibidas");
        this.filas = filas;
    }

    public int getFilas() {
        return filas;
    }

    public void setColumnas(int columnas) {
        System.out.println("Columnas recibidas");
        this.columnas = columnas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setX_puntoS(int x_puntoS) {
        System.out.println("Punto inicial recibido");
        this.x_puntoS = x_puntoS;
    }

    public void setY_puntoS(int y_puntoS) {
        this.y_puntoS = y_puntoS;
    }

    public void setX_puntoF(int x_puntoF) {
        System.out.println("Punto final recibido");
        this.x_puntoF = x_puntoF;
    }

    public void setY_puntoF(int y_puntoF) {
        this.y_puntoF = y_puntoF;
    }

    public String[][] getRespLaberinto() {
        System.out.println("Laberinto actual");
        for( int i=0; i<filas; i++){
                    for( int j=0; j<columnas; j++){
                        System.out.print(respLaberinto[i][j]);
                    }
         System.out.println();
        }
        return respLaberinto;
    }
      
    public void otroMetodo() {
//
//
    }
    public static void main(String[] args) {
        try {
          MiInterfazRemota mir = new MiClaseRemota();
          //  java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":" + args[0] + "/PruebaRMI", mir);
          Registry rmi = LocateRegistry.createRegistry(1234);
          rmi.rebind("PruebaRMI", mir);
          System.out.println("Servidor ON");
        } catch (Exception e) {
        }  
    }

}
