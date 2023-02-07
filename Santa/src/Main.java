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
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Cada enum nos servira de referencia para saber en que estadose encentra cada hilo
enum EstadoReno{
    Resting,
    Waiting,
    Working
}

enum EstadoElfo{
    Aiuda,
    Working
}

enum EstadoSanta{
    Sleeping,
    Helping,
    Giving
}

public class Main extends JFrame implements Runnable{

    private BufferedImage buffer, buferPantalla;
    private JPanel panelDibujo;
    private Thread hilo;
    
    private static ArrayList<Elfo> elfos;
    private static ArrayList<Reno> renos;
    private static Santa santa;
    private static int cantElfos=0;
    private static int[] ElfosPosX, ElfosPosY, RenosPosX, RenosPosY;

    
    public Main(){
        //Dibujamos el panel
        setTitle("El problema de Santa Claus");
        setSize(1200,600);
        setLayout(null);
        
        panelDibujo=new JPanel();
        panelDibujo.setBounds(0,0,1200,600);
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
    
    public static void main(String[] args) {
        new Main();
        
        // Iniciamos hilo de santa
	santa = new Santa();
	santa.start();
		
        //Elfos
        elfos = new ArrayList<Elfo>();
        
        //Creamos arrays de pocisiones para los Elfos 
        ElfosPosX = new int[]{50,50+200,250+200,450+200,650+200,850+200};
        ElfosPosY = new int[]{50,50,50,50,50,50,50,50,50};
        
	//Renos
        renos = new ArrayList<Reno>();
        ArrayList<String> NombresRenos = new ArrayList<String>(     
        Arrays.asList("Vondín","Danzarín","Chiqui","Juguetón","Cometa","Cupido","Trueno","Relámpago","Rodolfo"));
        
        //Creamos arrays de pocisiones para los Renos
        RenosPosX = new int[]{0,133,266,399,532,665,798,931,1064};
        RenosPosY = new int[]{450,450,450,450,450,450,450,450,450};
        
        //Aqui creamos cada elfo, en mi caso son 6
        for(int i = 1; i < 7; i++){
            Elfo elfo = new Elfo(santa, cantElfos, ElfosPosX[cantElfos], ElfosPosY[cantElfos]);
            elfos.add(elfo);
            cantElfos++;
            elfo.start();
	}
        
        //Aqui creamos cada Reno, 9 en total       
	for(int i = 1; i <= 9; i++){
            Reno reno = new Reno(santa,NombresRenos.get(i-1), RenosPosX[i-1], RenosPosY[i-1]);
            renos.add(reno);
	    reno.start();
	}
    }
    
    //Funcion que sirve para pintar el panel
    public void pintarPanel(){
        buferPantalla = new BufferedImage(panelDibujo.getWidth(),panelDibujo.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics ig=buferPantalla.getGraphics(); 
        
        Graphics2D ig2 = buferPantalla.createGraphics();
        ig2.setBackground(Color.white);
        ig2.clearRect(0, 0, this.getWidth(),this.getHeight());
        
        //Colores de cada parte de mis figuras
        Color piel = new Color(255,226,198);
        Color sombreroElfo = new Color(0,162,232);
        Color piel_santa = new Color(255,215,198);
        Color cafeObscuro = new Color(124,78,52);
        Color cafeClaro = new Color(185,122,87);
        Color amarilloClaro = new Color(255,255,168);
        
        //Creamos el dibujo de los elfos
        for(Elfo elfo:elfos){
            //Obtenemos coordenadas de la posicion del elfo
            int elfoX = elfo.getX(), elfoY = elfo.getY()-30;        
            
            //En caso de necesitar ayuda aparece un signo de interrogacion
            if(elfo.getEstado()==EstadoElfo.Aiuda){
                ig2.setColor(Color.red);
                ig2.fillRect(elfoX+120,elfoY+30,10,30);
                ig2.fillRect(elfoX+120,elfoY+65,10,10);
                
            }
            
            //Esta es la cara del elfo
            ig2.setColor(sombreroElfo);
            ig2.fillRect(elfoX+5, elfoY,100,30);
            ig2.setColor(Color.black);
            ig2.drawRect(elfoX-35, elfoY+29,100,30);
            ig2.drawRect(elfoX+80, elfoY+29,30,30);
            ig2.setColor(piel);
            ig2.fillRect(-25+elfoX, 60+elfoY,80,50);
            ig2.setColor(Color.black);
            ig2.fillRect(elfoX-3, elfoY+70,13,13);
            ig2.fillRect(elfoX+23, elfoY+70,13,13);
            ig2.fillRect(elfoX+4, elfoY+90,25,10);
            ig2.drawRect(-25+elfoX,elfoY+59,80,50);
            ig2.drawRect(elfoX+5, elfoY-1,100,30);
            ig2.drawRect(elfoX-25, elfoY+109,30,20);
            ig2.drawRect(elfoX+25, elfoY+109,30,20);
        }
        
        //Obtenemos la posicion de santa
        int santa_clausX = santa.getX(), santa_clausY = santa.getY()+100;
        
        //Con getEstado verificamos el estado de santa (ayudando, dormido, repartiendo regalos)
        EstadoSanta estadoSanta = santa.getEstado();
        
        if(estadoSanta!=EstadoSanta.Giving){
            
            //Si santa esta ayudando aparece un letrero de trabajando
            if(estadoSanta==EstadoSanta.Helping){
                ig2.setColor(Color.black);
                Font fuenteZ = new Font("TimesRoman", Font.BOLD, 14);
                ig2.drawRect(santa_clausX-35, santa_clausY-85,140,30);
                fuenteZ = new Font("TimesRoman", Font.BOLD, 20);
                ig2.setFont(fuenteZ);
                ig2.drawString("WORKEANDO", santa_clausX-30, santa_clausY-60);
            }
            
            //sino aparece un letrero que nos indica que santa esta durmiendo
            else{
                ig2.setColor(Color.black);
                Font fuenteZ = new Font("TimesRoman", Font.BOLD, 14);
                ig2.drawRect(santa_clausX-35, santa_clausY-85,100,30);
                fuenteZ = new Font("TimesRoman", Font.BOLD, 20);
                ig2.setFont(fuenteZ);
                ig2.drawString("MIMIDO", santa_clausX-20, santa_clausY-60);
            }
            
            //Creamos a santa
            ig2.setColor(Color.red);
            ig2.fillRect(-15+santa_clausX, santa_clausY-20,100,30);
            ig2.setColor(Color.black);
            ig2.drawRect(-39+santa_clausX, santa_clausY+10,100,30);
            ig2.drawRect(santa_clausX+70, santa_clausY+10,30,30);
            ig2.setColor(piel);
            ig2.fillRect(-29+santa_clausX, 41+santa_clausY,80,30);
             ig2.setColor(Color.black);
            ig2.fillRect(-10+santa_clausX, 50+santa_clausY,13,13);
            ig2.fillRect(santa_clausX+20, 50+santa_clausY,13,13);
            ig2.drawRect(-29+santa_clausX, santa_clausY+71,80,35);
            ig2.fillRect(santa_clausX, santa_clausY+81,23,13);
        }
        else{
            //Santa Está repartiendo
                ig2.setColor(Color.black);
                Font fuenteZ = new Font("TimesRoman", Font.BOLD, 14);
                ig2.drawRect(santa_clausX-165, santa_clausY-35,330,30);
                fuenteZ = new Font("TimesRoman", Font.BOLD, 20);
                ig2.setFont(fuenteZ);
                ig2.drawString("ME FUI A REPARTIR REGALOS", santa_clausX-150, santa_clausY-10);
            
        }
        
        //Se dibujan los renos una vez que llegan al establo de sus vacaciones
        for(Reno reno: renos){
            if(reno.getEstado()==EstadoReno.Waiting){
                int renoX = reno.getX(); int renoY = reno.getY();
                ig2.setColor(cafeClaro);
                ig2.fillRect(renoX+10,renoY,80,80);
                ig2.fillRect(renoX+90,renoY,20,20);
                ig2.fillRect(renoX-10,renoY,20,20);
                ig2.setColor(amarilloClaro);
                ig2.fillRect(renoX+10,renoY+40,80,40);
                ig2.setColor(cafeObscuro);
                ig2.fillRect(renoX+30,renoY+40,40,30);
                ig2.fillRect(renoX+80,renoY-15,10,10);
                ig2.fillRect(renoX+10,renoY-25,10,10);
                ig2.fillRect(renoX+20,renoY-30,10,30);
                ig2.fillRect(renoX+70,renoY-30,10,30);
                ig2.setColor(Color.black);
                ig2.fillRect(renoX+35,renoY+15,13,13);
                ig2.fillRect(renoX+55,renoY+15,13,13);
                ig2.drawRect(renoX+90,renoY,20,20);
                ig2.drawRect(renoX+10,renoY,80,80);
                ig2.drawRect(renoX-10,renoY,20,20);
                ig2.drawRect(renoX+20,renoY-30,10,30);
                ig2.drawRect(renoX+70,renoY-30,10,30);
                ig2.drawRect(renoX+80,renoY-15,10,10);
                ig2.drawRect(renoX+10,renoY-25,10,10);
                
            }
        }
        
        panelDibujo.getGraphics().drawImage(buferPantalla, 0, 0, this);
    }  

    @Override
    public void run() {
       while(true){
           try {
               
               Thread.sleep(1000);
               pintarPanel();
           } catch (Exception e) {
               //System.out.println(e);
           }
       }
    }
    
}

