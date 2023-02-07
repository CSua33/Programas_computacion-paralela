package RMI;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public interface MiInterfazRemota extends java.rmi.Remote {
    public String miMetodo1() throws java.rmi.RemoteException;
    public String[][] secuencial() throws java.rmi.RemoteException;
    public String[][] forkJoin() throws java.rmi.RemoteException;
    public String[][] executor() throws java.rmi.RemoteException;
    public String getTiempo()throws java.rmi.RemoteException;
    public void setFilas(int filas)throws java.rmi.RemoteException;
    public void setColumnas(int columnas)throws java.rmi.RemoteException;
    public void setX_puntoS(int x_puntoS)throws java.rmi.RemoteException;
    public void setY_puntoS(int y_puntoS)throws java.rmi.RemoteException;
    public void setX_puntoF(int x_puntoF)throws java.rmi.RemoteException;
    public void setY_puntoF(int y_puntoF)throws java.rmi.RemoteException;
    public String[][] getRespLaberinto()throws java.rmi.RemoteException;
    public int getFilas()throws java.rmi.RemoteException;
    public int getColumnas()throws java.rmi.RemoteException;
    
}
