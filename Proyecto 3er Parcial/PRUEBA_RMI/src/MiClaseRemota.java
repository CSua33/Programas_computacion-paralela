
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

    public MiClaseRemota() throws java.rmi.RemoteException {
// CÃ³digo del constructor
    }

    public  String miMetodo1() throws java.rmi.RemoteException {
// AquÃ­ ponemos el cÃ³digo que queramos
        //System.out.println("Hola mundo");
        return "Hola mundo";
    }
        public void miMetodo2() throws java.rmi.RemoteException {
// AquÃ­ ponemos el cÃ³digo que queramos
        System.out.println("Soy Felipe");
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
