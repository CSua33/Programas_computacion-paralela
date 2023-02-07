package canibales;

import canibales.Cocinero;
import canibales.Olla;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JFrame implements Runnable{
    static int MaxCant=5;
    
    private static LinkedList<Canibal> canibales;
    
    private static Olla olla;
    private static Cocinero cocinero;
    private static Thread productor;
    private static Thread [] consumidores;
    private static final Random numAleatorio = new Random();
     
    private BufferedImage buffer, buferPantalla;
    private JPanel panelDibujo;
    private Thread hilo;
    
    public Main(){
        setTitle("Los caníbales comensales");
        setSize(800,500);
        setLayout(null);
        
        panelDibujo=new JPanel();
        panelDibujo.setBounds(0,0,800,500);
        panelDibujo.setBackground(Color.white);
        panelDibujo.setLayout(null);
        add(panelDibujo);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        buferPantalla = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        
        hilo = new Thread(this);
        hilo.start();
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Main();
        
        canibales=new LinkedList<Canibal>();
        
        int CantCanibales = numAleatorio.nextInt(21)+1;
        //int CantConsumidores = 21;
        System.out.println("Cantidad de canibales: " + CantCanibales);
        olla = new Olla();
        cocinero=new Cocinero(olla,1,MaxCant);
        productor = new Thread(cocinero);
        consumidores = new Thread[CantCanibales];
 
        productor.start();
        
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
         
        for(int i = 0; i < CantCanibales; i++){
            Canibal tempCanibal=new Canibal(olla,i);
            consumidores[i] = new Thread(tempCanibal);
            consumidores[i].start();
            canibales.add(tempCanibal);
        }  
    }    
    
    public void pintarPanel(){
        buferPantalla = new BufferedImage(panelDibujo.getWidth(),panelDibujo.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics ig=buferPantalla.getGraphics(); 
        
        Graphics2D ig2 = buferPantalla.createGraphics();
        Color pasto = new Color(192,254,200);
        ig2.setBackground(pasto);
        ig2.clearRect(0, 0, this.getWidth(),this.getHeight());
        
        //cocinero
        Color piel = new Color(254,219,184);
        Color cafe = new Color(185,122,87);
        ig2.setColor(piel);
        ig2.fillRect(cocinero.getX(), cocinero.getY(), 45, 35);
        ig2.setColor(Color.white);
        ig2.fillRect(cocinero.getX()+5, cocinero.getY()+35, 35, 25);
        ig2.fillRect(cocinero.getX()+15, cocinero.getY()-20, 15, 20);
        ig2.fillRect(cocinero.getX()+10, cocinero.getY()-25, 25, 5);
        ig2.setColor(Color.black);
        ig2.drawRect(cocinero.getX(), cocinero.getY(), 45, 35);
        ig2.drawRect(cocinero.getX()+5, cocinero.getY()+35, 35, 25);
        ig2.drawRect(cocinero.getX()+15, cocinero.getY()-20, 15, 20);
        ig2.drawRect(cocinero.getX()+10, cocinero.getY()-25, 25, 5);
        ig2.fillOval(cocinero.getX()+15, cocinero.getY()+45, 5, 5);
        ig2.fillOval(cocinero.getX()+15, cocinero.getY()+50, 5, 5);
        ig2.fillOval(cocinero.getX()+25, cocinero.getY()+45, 5, 5);
        ig2.fillOval(cocinero.getX()+25, cocinero.getY()+50, 5, 5);
        ig2.fillOval(cocinero.getX()+25, cocinero.getY()+3, 10, 15);
        ig2.fillOval(cocinero.getX()+10, cocinero.getY()+3, 10, 15);
        ig2.setColor(Color.red);
        ig2.fillRect(cocinero.getX()+5, cocinero.getY()+35, 15, 10);
        ig2.fillRect(cocinero.getX()+25, cocinero.getY()+35, 15, 10);
        ig2.setColor(cafe);
        ig2.fillRect(cocinero.getX()+7, cocinero.getY()+20, 15, 10);
        ig2.fillRect(cocinero.getX()+23, cocinero.getY()+20, 15, 10);
        //Olla
        ig2.setColor(Color.black);
        ig2.fillRect(498-400,150,104,100);
        ig2.fillRect(478-400,150,20,20);
        ig2.fillRect(602-400,150,20,20);
        Color comida = new Color(193,237,253);
        ig2.setColor(comida);
      
        for(int i=0;i<olla.getContenido();i++){
            ig2.fillRect(502-400, 385-(i*(100/MaxCant)+152), 97, (100/MaxCant)-3);
        }
        
        int x=0;
        //Canibal
        Color cabeza = new Color(239,228,176);
        Color dientes = new Color(255,255,128);
        for(Canibal canibal: canibales){
            x+=30;
            if(!canibal.isFinalizado()){
                //ig2.drawOval(canibal.getX(), canibal.getY(), 35, 35);
                
                ig2.setColor(cabeza);
                ig2.fillRect(canibal.getX()-5, canibal.getY(), 45, 35);
                ig2.setColor(Color.red);
                ig2.fillOval(canibal.getX()+4, canibal.getY()+5, 10, 15);
                ig2.fillOval(canibal.getX()+20, canibal.getY()+5, 10, 15);
                ig2.setColor(Color.yellow);
                ig2.fillOval(canibal.getX()+7, canibal.getY()+8, 4, 8);
                ig2.fillOval(canibal.getX()+23, canibal.getY()+8, 4, 8);
                ig2.setColor(Color.black);
                ig2.drawOval(canibal.getX()+7, canibal.getY()+8, 4, 8);
                ig2.drawOval(canibal.getX()+23, canibal.getY()+8, 4, 8);
                ig2.drawOval(canibal.getX()+4, canibal.getY()+5, 10, 15);
                ig2.drawOval(canibal.getX()+20, canibal.getY()+5, 10, 15);
                ig2.setColor(dientes);
                ig2.fillRect(canibal.getX()+7, canibal.getY()+22, 20, 10);
                ig2.setColor(Color.black);
                ig2.drawRect(canibal.getX()+7, canibal.getY()+22, 20, 10);
                ig2.drawRect(canibal.getX()+7, canibal.getY()+26, 20, 1);
                ig2.setColor(Color.orange);
                ig2.fillRect(canibal.getX(), canibal.getY()+35, 35, 25);
                ig2.setColor(piel);
                ig2.fillRect(canibal.getX()+20, canibal.getY()+35, 15, 15);
                ig2.fillRect(canibal.getX()+35, canibal.getY()+38, 10, 5);
                ig2.setColor(cafe);
                ig2.fillRect(canibal.getX()+45, canibal.getY()+35, 5, 25);
                ig2.setColor(Color.gray);
                ig2.fillOval(canibal.getX()+43, canibal.getY()+22, 8, 15);
                //ig2.setColor(Color.black);
                //ig2.drawString(""+canibal.getIdconsumidor(),canibal.getX()+13, canibal.getY()+23);
                
            }
        }
        
        panelDibujo.getGraphics().drawImage(buferPantalla, 0, 0, this);
    }
    

    @Override
    public void run() {
        while(true){
             try {
                Thread.sleep(10);
                pintarPanel();
            } catch (Exception e) {
                //System.out.println(e);
            }    
        }
    }
    
}

