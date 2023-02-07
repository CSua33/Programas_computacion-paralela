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
    public void miMetodo2() throws java.rmi.RemoteException;
    
}
