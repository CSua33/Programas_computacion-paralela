import java.lang.Thread;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public class Puente extends Thread{
  public  Queue<coches> autosNorte=new LinkedList<coches>();
  public Queue<coches> autosSur=new LinkedList<coches>();
  public int MutexN=1;//1 desocupado , 0 ocupado
  public int MutexS=1;
  public int semaforo=1;//1 desocupado , 0 ocupado
  public int recorrido=0;//1 recorrio el puente
  public coches respaldoAuto;
  
  public void waitxN(coches S){
       if (MutexN>0&&semaforo==1){
             MutexN=0;
             semaforo=0;
             respaldoAuto=S;
             
     }
     else{ 
         S.suspend();
         autosNorte.add(S);
     }
  }
  
  public void signalxN(){
       if (autosNorte.size()>0){
           respaldoAuto=autosNorte.remove();
             respaldoAuto.resume();
     }
     else{ 
           if(respaldoAuto.getRecorrido()==1){
        MutexN=1;
           }
     }
  }
  
  public void waitxS(coches S){
     if (MutexS>0&&semaforo==1){
             MutexS=0;
              semaforo=0;
              respaldoAuto=S;
     }
     else{ 
         S.suspend();
         autosSur.add(S);
         
     }
  }
   public void signalxS(){
       if (autosSur.size()>0){
           respaldoAuto=autosSur.remove();
             respaldoAuto.resume();
     }
     else{ 
           if(respaldoAuto.getRecorrido()==1){
        MutexS=1;
           }
     }
  }
   public void accesoCoches(coches S){
        
        
       if(S.getSentido()==Origen.Norte){
           S.start();
           waitxN(S);
       }
       if(S.getSentido()==Origen.Sur){
           S.start();
           waitxS(S);
       }
        
   }

    public Queue<coches> getAutosNorte() {
        return autosNorte;
    }

    public Queue<coches> getAutosSur() {
        return autosSur;
    }
    
    public int getAutosNorteSize() {
        return autosNorte.size();
    }

    public int getAutosSurSize() {
        return autosSur.size();
    }
   

    public void run(){
       while(true){
           try {
               this.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
           }
           if(semaforo==0){
                 if(MutexN==0){
                    signalxN();
                   // System.out.println("Auto norte");
                }
                if(MutexS==0){
                    signalxS();
                   // System.out.println("Auto sur");
                }
                if(MutexN==1&&MutexS==1){
                    if(autosNorte.size()>0){
                        MutexN=0;
                    }
                    if(autosSur.size()>0){
                        MutexS=0;
                    }
                }
                if(MutexN==1&&MutexS==1){
                    semaforo=1;
                    //System.out.println("Reinicio");
                }
           }
           else{
               System.out.println("Puente vacio");
               try {
                   this.sleep(1000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    }
            
}
        