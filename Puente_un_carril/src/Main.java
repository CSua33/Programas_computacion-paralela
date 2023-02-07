/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.Object;
import java.lang.Thread;
import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
//////////////////////////////////////////////////////////////////////
 enum Origen{
    Norte{
        public String toString() {
            return "Norte";
        }
    },
    Sur{
        public String toString() {
            return "Sur";
        }
    };
}
 //////////////////////////////////////////////////////////////////////
public class Main extends JFrame implements Runnable{
    
    
//////////////////////////////////////////////////////////////////////
    private Thread hilo;
    private BufferedImage buffer, buferPantalla;
    private JPanel panelDibujo;
    private int i=0,i2=0;
    private static Puente puente;
    private Queue<coches> transitoNorte, transitoSur;//Puente solo tiene referencia de los que avanzan
   ////////////////////////////////////////////////////////////////////// 
    public Main() {
        transitoNorte=new LinkedList<coches>();
        transitoSur=new LinkedList<coches>();
        
        setTitle("Puente de un solo carril");
        setSize(800,550);
        setLayout(null);
        
        panelDibujo=new JPanel();
        panelDibujo.setBounds(0,0,800,350);
        panelDibujo.setBackground(Color.white);
        panelDibujo.setLayout(null);
        add(panelDibujo);
        
       
        Font btnLetra=new Font("Tahoma", Font.BOLD, 15);
        JPanel panel = new JPanel();
        panel.setBounds(0,350,800,200);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);
        add(panel);
        
        JButton btnAutoNorte = new JButton("N");
        btnAutoNorte.setForeground(Color.white);
        btnAutoNorte.setBackground(Color.red);
        btnAutoNorte.setFont(btnLetra);
        btnAutoNorte.setBounds(300,50,80,50);
        btnAutoNorte.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){
                    coches autoNorte=new coches(Origen.Norte,i,puente.getAutosNorteSize());
                    puente.accesoCoches(autoNorte);
                    transitoNorte.add(autoNorte);
                    i++;
                }
                

            }    
        );
        
        panel.add(btnAutoNorte);
        
        JButton btnAutoSur = new JButton("S");
        btnAutoSur.setForeground(Color.white);
        btnAutoSur.setBackground(Color.blue);
        btnAutoSur.setFont(btnLetra);
        btnAutoSur.setBounds(400,50,80,50);
        btnAutoSur.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent a){
                    coches autoSur=new coches(Origen.Sur,i,puente.getAutosSurSize());
                    puente.accesoCoches(autoSur);
                    transitoSur.add(autoSur);
                    i++;
                }
            }    
        );
        
        panel.add(btnAutoSur);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        buferPantalla = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        
        hilo = new Thread(this);
        hilo.start();
        
        setVisible(true);
    }
    
    public static void main(String args[]){      

        puente= new Puente();
        puente.start();
                new Main(); 
                
    }
    
        public void panel(){
        buferPantalla = new BufferedImage(panelDibujo.getWidth(),panelDibujo.getHeight(), BufferedImage.TYPE_INT_RGB);
   
        Graphics2D ig2 = buferPantalla.createGraphics();
        Color pasto=new Color(112, 250,137);
        ig2.setBackground(pasto);
        ig2.clearRect(0, 0, this.getWidth(),this.getHeight());
        
        //Puente
        ig2.setColor(Color.black);
        ig2.fillRect(160, 245, 480, 52);
        ig2.setColor(Color.yellow);
        ig2.fillRect(165, 265, 70, 8);
        ig2.fillRect(265, 265, 70, 8);
        ig2.fillRect(365, 265, 70, 8);
        ig2.fillRect(465, 265, 70, 8);
        ig2.fillRect(565, 265, 70, 8);
        //Auto norte
        Color coche1=new Color(230, 70,225);        
        for(coches auto: transitoNorte){
            ig2.setColor(coche1);
            ig2.fillRect(auto.getX(),auto.getY(), 70, 40);
            ig2.setColor(Color.white);
            ig2.fillRect(auto.getX()+5,auto.getY()+5, 30, 30);
            ig2.setColor(Color.black);
            ig2.fillRect(auto.getX()+5,auto.getY()+5, 10, 30);
            ig2.fillRect(auto.getX()+30,auto.getY()+5, 20, 30);
            ig2.setColor(Color.yellow);
            ig2.fillOval(auto.getX()+65,auto.getY()+5, 5, 10);
            ig2.fillOval(auto.getX()+65,auto.getY()+25, 5, 10);
            
        }
        //Auto sur
        for(coches auto: transitoSur){
            ig2.setColor(Color.red);
            ig2.fillRect(auto.getX(),auto.getY(), 70, 40);
            ig2.setColor(Color.white);
            ig2.fillRect(auto.getX()+25,auto.getY()+5, 30, 30);
            ig2.setColor(Color.black);
            ig2.fillRect(auto.getX()+55,auto.getY()+5, 10, 30);
            ig2.fillRect(auto.getX()+15,auto.getY()+5, 20, 30);
            ig2.setColor(Color.yellow);
            ig2.fillOval(auto.getX(),auto.getY()+5, 5, 10);
            ig2.fillOval(auto.getX(),auto.getY()+25, 5, 10);
        }
        
        panelDibujo.getGraphics().drawImage(buferPantalla, 0, 0, this);
    }
 
    
    private void actualizarLista(){
        if(!transitoNorte.isEmpty()){
            if(transitoNorte.element().getRecorrido()==1){
                transitoNorte.remove();
            }
        }
       
        if(!transitoSur.isEmpty()){
            if(transitoSur.element().getRecorrido()==1){
                transitoSur.remove();
            }
        }
    }
 

        
    @Override
    public void run() {
       while(true){
            try{
                panel();
                actualizarLista();
                hilo.sleep(100);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
        
     

}