
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */

public class coches extends Thread{
            private Origen origen;
            private int inicioX, x, y, posicion;
            private int recorrido;//1 recorrio el puente - bandera auto pasó puente
            
            coches(Origen origen,int posicion,int autosAnteriores){
               //this.sentido=l;
               //this.recorrido=0; 
               this.recorrido=0;
               this.posicion=posicion;
               this.origen=origen;
        
               if(origen==Origen.Norte){
                this.x=100-(autosAnteriores*75);
                this.y=200;
               }
               else{
                this.x=650+(autosAnteriores*75);
                this.y=200;
               }
        
                //System.out.println("Coche "+posicion+" creado "+origen.toString());
            }


 
          public void run(){
             if(origen==Origen.Norte){
            while(x<700){
                x+=2;
                if(y<250){
                    y+=2;
                }
                //System.out.println("Coche "+posicion+": "+x);
                try {
                    this.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                } 
            }
            }
           else{
            while(x>50){
                x-=2;
                if(y<250){
                    y+=2;
                }
                //System.out.println("Coche "+posicion+": "+x);
                try {
                    this.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                } 
            }
        }
        
        this.recorrido=1;
        //System.out.println("Finalizado coche "+posicion);

          } 
  

    public Origen getSentido() {
        return origen;
    }
    
    public void setSentido(Origen origen) {
        this.origen = origen;
    }
 
    public int getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
      
}
