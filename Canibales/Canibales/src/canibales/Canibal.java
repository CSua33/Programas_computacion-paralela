package canibales;


public class Canibal implements Runnable
{
    private Olla olla;
    private int idCanibal, x, y;
    private boolean finalizado;
    
    public Canibal(Olla olla, int idcanibal){
        this.olla = olla;
        this.idCanibal = idcanibal;
        finalizado=false;
        if(idCanibal>=7&&idCanibal<14){
            x=700-(((idcanibal-7)*62)+5);
            y=180;
        }
        else if(idCanibal>=14){
            x=700-(((idcanibal-14)*62)+5);
            y=250;
        }
        else{
            x=700-(((idcanibal)*62)+5);
            y=110;
        }
    }
 
    @Override
    public void run() 
    {
        System.out.println("El canibal " + idCanibal + " consume: " + olla.get(this));
    }
    
    public void Avanzar(){
        while(x>270){
            x-=3;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
        
        finalizado=true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getIdconsumidor() {
        return idCanibal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}