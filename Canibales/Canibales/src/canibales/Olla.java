package canibales;


public class Olla{
    private int contenido;
    private boolean ollaLlena = false;
 

    public synchronized int get(Canibal canibal)
    {
        while (!ollaLlena)
        {
            try
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
       
        canibal.Avanzar();
        
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        contenido--;    
        
        if (contenido==0){
            ollaLlena = false;
        }
        
        notify();
        return contenido;
    }
 
  
    public synchronized void put(int value) 
    {
        while (ollaLlena) 
        {
            try
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Contenedor: Error en put -> " + e.getMessage());
            }
        }
        
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        contenido = value;
        ollaLlena = true;
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        notify();
    }

    public int getContenido() {
        return contenido;
    }
    
}