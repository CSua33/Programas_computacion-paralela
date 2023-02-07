/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
import static java.lang.Thread.sleep;
import java.util.Random;

public class Reno extends Thread {

    private Santa santa;
    private Random rnd;
    public boolean espera;
    private String name;
    private int x,y;
    private EstadoReno estado;

    public Reno(Santa santa, String name, int x, int y) {
        this.santa = santa;
        rnd = new Random();
        espera = false;
        this.name = name;
        this.x = x;
        this.y = y;
        estado = EstadoReno.Resting;
    }

    String getname() {
        return this.name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int pause = rnd.nextInt(30001) + 10000;
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //Esta parte cambia el estado del Reno y se lo pasa al main para que pueda dibujarse
            //System.out.println("Reno " + getname() + " entra al establo");
            estado = EstadoReno.Waiting;
            
            //Cuando un reno entra al establo, espera el momento de iniciar el trabajo
            santa.entraEstablo(this);
            espera = true;
            synchronized (this) {
                while (espera) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Despues de que se entregan los regalos los renos se van de vacaciones
            System.out.println("Reno " + getname() + " se va de vacaciones");
            estado = EstadoReno.Resting;
            vacaciones();
        }
    }

    public void vacaciones() {
        try {
            int pause = rnd.nextInt(30001) + 10000; // 10 - 40s
            //int pause =1000;
            sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setEstado(EstadoReno estado) {
        this.estado = estado;
    }

    public EstadoReno getEstado() {
        return estado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "Reno " + name;
    }
}

