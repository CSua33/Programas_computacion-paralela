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
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MiClienteRMI extends JFrame implements ActionListener{
    
    JTextArea area;
    JLabel filas;
    JLabel columnas;
    JLabel x_puntoS;
    JLabel y_puntoS;
    JLabel x_puntoF;
    JLabel y_puntoF;
    JLabel tiemp;
    JTextArea ingresar_filas;
    JTextArea ingresar_columnas;
    JTextArea ingresar_x_puntoS;
    JTextArea ingresar_y_puntoS;
    JTextArea ingresar_x_puntoF;
    JTextArea ingresar_y_puntoF;
    JTextArea verTiempo;
    JButton enviarFilas;
    JButton enviarColumnas;
    JButton enviarPuntoS;
    JButton enviarPuntoF;
    JButton botonSecuencial;
    JButton botonForkJoin;
    JButton botonExecutor;
    JButton botonLabAct;
    int row;
    int col;
    String tiempo;
    String[][] laberinto=new String[row][col];
    public MiClienteRMI(){   
        //Creamos el JFrame
        setTitle("MiClienteRMI");
        setLayout(null);
        setSize(500,500);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Filas
        filas = new JLabel("Filas: "); 
        filas. setBounds(10, 10, 50, 20); 
        add(filas);
        ingresar_filas = new JTextArea("");
        ingresar_filas.setBounds(90, 10, 50, 20);
        add(ingresar_filas);
        enviarFilas = new JButton("Enviar Filas");
        enviarFilas.setBounds(200, 10, 100, 20);
        add(enviarFilas);
        enviarFilas.addActionListener(this);
        //Columnas
        columnas = new JLabel("Columnas: "); 
        columnas. setBounds(10, 15, 70, 70); 
        add(columnas);
        ingresar_columnas = new JTextArea("");
        ingresar_columnas.setBounds(90, 40, 50, 20);
        add(ingresar_columnas);
        enviarColumnas = new JButton("Enviar Columnas");
        enviarColumnas.setBounds(200, 40, 130, 20);
        add(enviarColumnas);
        enviarColumnas.addActionListener(this);
        //Punto S
        x_puntoS = new JLabel("Punto_S X : "); 
        x_puntoS. setBounds(10, 20, 70, 120); 
        add(x_puntoS);
        ingresar_x_puntoS = new JTextArea("");
        ingresar_x_puntoS.setBounds(90, 70, 50, 20);
        add(ingresar_x_puntoS);
        y_puntoS = new JLabel("Punto_S Y : "); 
        y_puntoS. setBounds(10, 25, 70, 170); 
        add(y_puntoS);
        ingresar_y_puntoS = new JTextArea("");
        ingresar_y_puntoS.setBounds(90, 100, 50, 20);
        add(ingresar_y_puntoS);
        enviarPuntoS = new JButton("Enviar Punto S");
        enviarPuntoS.setBounds(200, 70, 150, 20);
        add(enviarPuntoS);
        enviarPuntoS.addActionListener(this);
        //Punto F
        x_puntoF = new JLabel("Punto_F X : "); 
        x_puntoF. setBounds(10, 30, 70, 220); 
        add(x_puntoF);
        ingresar_x_puntoF = new JTextArea("");
        ingresar_x_puntoF.setBounds(90, 130, 50, 20);
        add(ingresar_x_puntoF);
        y_puntoF = new JLabel("Punto_F Y : "); 
        y_puntoF. setBounds(10, 35, 70, 270); 
        add(y_puntoF);
        ingresar_y_puntoF = new JTextArea("");
        ingresar_y_puntoF.setBounds(90, 160, 50, 20);
        add(ingresar_y_puntoF);
        enviarPuntoF = new JButton("Enviar Punto F");
        enviarPuntoF.setBounds(200, 100, 150, 20);
        add(enviarPuntoF);
        enviarPuntoF.addActionListener(this);
        //Boton secuencial
        botonSecuencial = new JButton("Secuencial");
        botonSecuencial.setBounds(200, 130, 100, 20);
        add(botonSecuencial);
        botonSecuencial.addActionListener(this);
        //Boton ForkJoin
        botonForkJoin = new JButton("Fork/Join");
        botonForkJoin.setBounds(200, 160, 100, 20);
        add(botonForkJoin);
        botonForkJoin.addActionListener(this);
        //Boton Executor service
        botonExecutor = new JButton("Executor");
        botonExecutor.setBounds(200, 190, 100, 20);
        add(botonExecutor);
        botonExecutor.addActionListener(this);
        //Imprimir Laberinto encontrado
        area=new JTextArea();
        area.setBounds(30,250, 200,200);
        add(area);
        //Tiempo de ejecucion
        tiemp = new JLabel("Tiempo de ejecucion: "); 
        tiemp. setBounds(250, 250, 250, 20); 
        add(tiemp);
        verTiempo = new JTextArea("");
        verTiempo.setBounds(250,280, 150, 20);
        add(verTiempo);
        //Boton obtener laberinto actual
        botonLabAct = new JButton("Obtener Laberinto actual");
        botonLabAct.setBounds(250, 400, 200, 20);
        add(botonLabAct);
        botonLabAct.addActionListener(this);
        ///////////////////////////////////////
        setVisible(true);   //true para mostrar
       }
    
    public static void main(String[] args) {
        
         MiClienteRMI MiClienteRMI = new MiClienteRMI();
         
    }
   
        @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Registry rmi;
        try {
            rmi = LocateRegistry.getRegistry("192.168.0.106",1234);
            MiInterfazRemota mir = (MiInterfazRemota)rmi.lookup("PruebaRMI");
            //Funcionalidad Botones
            if(e.getSource() == enviarFilas){
            mir.setFilas(Integer.parseInt(ingresar_filas.getText()));
            row=Integer.parseInt(ingresar_filas.getText());
            System.out.println("Filas enviadas");
            }
            else if(e.getSource() == enviarColumnas){
            mir.setColumnas(Integer.parseInt(ingresar_columnas.getText()));
            col=Integer.parseInt(ingresar_columnas.getText());
            System.out.println("Columnas enviadas");
            }
            else if(e.getSource() == enviarPuntoS){
            mir.setX_puntoS(Integer.parseInt(ingresar_x_puntoS.getText()));
            mir.setY_puntoS(Integer.parseInt(ingresar_y_puntoS.getText()));
            System.out.println("Punto inicial enviado");
            }
            else if(e.getSource() == enviarPuntoF){
            mir.setX_puntoF(Integer.parseInt(ingresar_x_puntoF.getText()));
            mir.setY_puntoF(Integer.parseInt(ingresar_y_puntoF.getText()));
            System.out.println("Punto final enviado");
            }
            else if(e.getSource() == botonSecuencial){
            System.out.println("Algoritmo SECUENCIAL");
           
            laberinto= mir.secuencial();
                for(int j=0;j<row;j++){
                    for(int k=0;k<col;k++){
                        System.out.print(laberinto[j][k]);
                }
                    System.out.println();
                }
                tiempo=mir.getTiempo();
                System.out.println("Tiempo de ejecucion: " + tiempo);
                verTiempo.setText(tiempo);
                //Imprimir matriz
                String strMatriz = "";
                for( int i=0; i<row; i++){
                    for( int j=0; j<col; j++){
                        strMatriz+= "   ";
                    strMatriz+=laberinto[i][j];
                    strMatriz+= "   ";
                 }
                strMatriz+= "\n";
                }
                area.setText(strMatriz);
            }
            else if(e.getSource() == botonForkJoin){
            System.out.println("Algoritmo fORK/JOIN");
            
            laberinto= mir.forkJoin();
                for(int j=0;j<row;j++){
                    for(int k=0;k<col;k++){
                        System.out.print(laberinto[j][k]);
                }
                    System.out.println();
                }
                tiempo=mir.getTiempo();
                System.out.println("Tiempo de ejecucion: " + tiempo);
                verTiempo.setText(tiempo);
                //Imprimir matriz
                String strMatriz = "";
                for( int i=0; i<row; i++){
                    for( int j=0; j<col; j++){
                        strMatriz+= "   ";
                    strMatriz+=laberinto[i][j];
                    strMatriz+= "   ";
                 }
                strMatriz+= "\n";
                }
                area.setText(strMatriz);
               // 
            }
            else if(e.getSource() == botonExecutor){
            System.out.println("Algoritmo EXECUTOR");
            
            laberinto= mir.executor();
                for(int j=0;j<row;j++){
                    for(int k=0;k<col;k++){
                        System.out.print(laberinto[j][k]);
                }
                    System.out.println();
                }
                
                tiempo=mir.getTiempo();
                System.out.println("Tiempo de ejecucion: " + tiempo);
                verTiempo.setText(tiempo);
                //Imprimir matriz
                String strMatriz = "";
                for( int i=0; i<row; i++){
                    for( int j=0; j<col; j++){
                        strMatriz+= "   ";
                    strMatriz+=laberinto[i][j];
                    strMatriz+= "   ";
                 }
                strMatriz+= "\n";
                }
                area.setText(strMatriz);
            }
            else if(e.getSource() == botonLabAct){
                
                laberinto=mir.getRespLaberinto();
                System.out.println("Laberinto actual ");
                for(int j=0;j<row;j++){
                    for(int k=0;k<col;k++){
                        System.out.print(laberinto[j][k]);
                }
                    System.out.println();
                }
                tiempo=mir.getTiempo();
                System.out.println("Tiempo de ejecucion: " + tiempo);
                verTiempo.setText(tiempo);
                //Imprimir matriz
                String strMatriz = "";
                for( int i=0; i<row; i++){
                    for( int j=0; j<col; j++){
                        strMatriz+= "   ";
                    strMatriz+=laberinto[i][j];
                    strMatriz+= "   ";
                 }
                strMatriz+= "\n";
                }
                area.setText(strMatriz);
            }
            ////////////////////////////////////////////////////////////////////
        } 
        catch (RemoteException ex) {
            Logger.getLogger(MiClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MiClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}