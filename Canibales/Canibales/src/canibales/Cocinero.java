package canibales;

import canibales.Olla;
import java.util.Random;

public class Cocinero implements Runnable
{
    private final Random aleatorio;
    private final Olla olla;
    private final int idcocinero, TIEMPOESPERA = 500, MaxCant;
    private int x,y;

    
    public Cocinero(Olla olla, int idcocinero, int MaxCant) 
    {
        this.olla = olla;
        this.MaxCant=MaxCant;
        this.idcocinero = idcocinero;
        this.x=130;
        this.y=350;
        aleatorio = new Random();
    }
 
    @Override

    public void run(){
        while(true){
            //int poner = aleatorio.nextInt(300);
             Animacion();
            
            System.out.println("El Cocinero " + idcocinero + " pone: " + this.MaxCant);
            try
            {
                Thread.sleep(TIEMPOESPERA);
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Cocinero " + idcocinero + ": Error en run -> " + e.getMessage());
            }
        }
    }

    private void Animacion(){
        while(y>300){
            y--;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        olla.put(this.MaxCant);
        while(y<350){
            y++;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}