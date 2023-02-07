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
import java.util.Vector;


public class Santa extends Thread{
    //Estas variables sirven como contador para saber cuando despertar a santa
    private static final int NUM_ELFOS = 3;
    private static final int NUM_RENOS = 9;
	
    private Random rnd;
  
    //Aqui se iran encolando los renis y elfos hasta que sea necesario despertar a santa
    private Vector<Elfo> ListaElfosEspera; 
    private Vector<Reno> ListaRenosEspera; 

    private EstadoSanta estado;
    private int x,y;
    
    public Santa() {
        rnd = new Random();
	ListaElfosEspera = new Vector<Elfo>();
	ListaRenosEspera = new Vector<Reno>();
        estado=EstadoSanta.Sleeping;
        x=600;
        y=180;
    }

    @Override
    public void run() {
        while (true) {
            
            //Si no se a alcanzado el num necesario de elfos o renos santa permanece dormido
            synchronized (this) {
                while (ListaElfosEspera.size() < NUM_ELFOS && ListaRenosEspera.size() < NUM_RENOS) {
                    try {
                        wait();
		    } catch (InterruptedException e) {
                        e.printStackTrace();
		    }
		}
	    }
            
            //Cuando se alcanza el numero de renos santa se va con ellos 
            // La entrega de regalos tiene la mayor prioridad
	    if (ListaRenosEspera.size() >= NUM_RENOS) { 
                try {    
                    Thread.sleep(1000);          
                } catch (Exception e) {
                }
                //System.out.println("Santa despertó para entregar los regalos");
                estado=EstadoSanta.Giving;
                //Todos los renos cambian de estado para saber que no hay que dibujarlos
                for(Reno reno: this.ListaRenosEspera){
                    reno.setEstado(EstadoReno.Working);
                }
                
                //Se llama la funcion para irse a entregar los regalos
                //cuando santa regresa se va a dormir
		entregaRegalos();
		//System.out.println("Santa regresó de entregar los regalos y vuelve a dormir");
                //Se cambia el letrero de santa
                estado=EstadoSanta.Sleeping;
	    } 
            
            //Cuando se alcanza el numero de duendes que requieren ayuda santa despierta
            else if (ListaElfosEspera.size() >= NUM_ELFOS) {
                //System.out.println("Santa despertó para ayudar a los elfos");
                //Se cambia la etiqueta a trabajando
                estado=EstadoSanta.Helping;
                //Con esta funcion santa le dedica un tiempo a ayudar a cada duende
		ayudar();
		//System.out.println("Santa atendió a los elfos y vuelve a dormir");
                //Se cambia la etiqueta a durmiendo
                estado=EstadoSanta.Sleeping;
	    }
	}
    }

    public void preguntar(Elfo elfo) {
        this.ListaElfosEspera.add(elfo); 
	synchronized (this) {
            this.notify();
	}
    }

    public void entraEstablo(Reno reno) {
	this.ListaRenosEspera.add(reno); 
	synchronized (this) {
	    this.notify();
	}
    }

    private void ayudar() {
        for (int i = 0; i < ListaElfosEspera.size();) {
	    Elfo elfo = ListaElfosEspera.get(0);
	    synchronized (elfo) {
                try {
                    // Santa dedica un tiempo para asesorar a cada elfo
                    sleep(1000); 
		} catch (InterruptedException e) {
                    e.printStackTrace();
		}
                
                //El elfo regresa a lo suyo
                //System.out.println("Elfo " + elfo.getid() + " fue atendido");
	        elfo.espera = false;
                elfo.setEstado(EstadoElfo.Working);
		elfo.notify();
	    }
            
            // El elfo que ya fue asesorado se elimina de la lista
            //Con esto decrementa la lista y por tanto no hay que incrementar la i
	    ListaElfosEspera.remove(0);  
	}
    }

    private void entregaRegalos() {
        // Se van a entregar los regalos por un tiempo random
	try {
	    int pause = rnd.nextInt(8001) + 7000;
	    sleep(pause);
	} catch (InterruptedException e) {
            e.printStackTrace();
	}
	// Despues de entregar los regalos los renos se van de vacaciones
	for (int i = 0; i < NUM_RENOS; i++) {
	    Reno reno = ListaRenosEspera.get(0);
	    synchronized (reno) {
		reno.espera = false;
	        reno.notify();
	    }
	    ListaRenosEspera.remove(0);
	}
    }
    
    public EstadoSanta getEstado(){
        return estado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}

