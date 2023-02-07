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

public class Elfo extends Thread {

    private Santa santa;
    private Random rnd;
    private int id, x, y;

    private EstadoElfo estado;
    
    public boolean espera;

    public Elfo(Santa santa, int id, int x, int y) {
        this.santa = santa;
        rnd = new Random();
        espera = false;
        this.id = id;
        this.x = x;
        this.y = y;
        estado=EstadoElfo.Working;
    }

    int getid() {
        return this.id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Este tiempo sirve para que el elfo pida ayuda cada cierto tiempo
                int pause = rnd.nextInt(19001) + 3000;
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Esta parte cambia el estado del duende y se lo pasa al main para que pueda dibujarse
           //System.out.println("Elfo " + id + " necesita ayuda");
            estado=EstadoElfo.Aiuda;
            
            // Cuando un elfo necesita ayuda le pregunta a Santa
            santa.preguntar(this);
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
        }
    }

    @Override
    public String toString() {
        return "Elfo " + id;
    }

    public EstadoElfo getEstado() {
        return estado;
    }

    public void setEstado(EstadoElfo estado) {
        this.estado = estado;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}