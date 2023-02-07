
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MiClienteRMI2 {
    public static void main(String[] args) {
        try {

            //MiInterfazRemota mir = (MiInterfazRemota) Naming.lookup("//" + args[0] + ":" + args[1] + "/PruebaRMI");
         
          Registry rmi = LocateRegistry.getRegistry("192.168.0.106",1234);
          MiInterfazRemota mir = (MiInterfazRemota)rmi.lookup("PruebaRMI");
          mir.miMetodo2();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}