package Modelo;


public abstract class ObjetoJuego{
   protected int x;
   protected int y;
   private int velocidad;
   private Observador observador;
    	private int xMax;
    	private int yMax;
    

   public ObjetoJuego(int x, int y, int velocidad, Observador observador, int anchoEspacio, int altoEspacio) {
    super();  
      this.x = x;
      this.y = y;
      this.velocidad = velocidad;
      this.observador = observador;
      observador.mover(x,y);
     this.xMax = anchoEspacio - observador.getAncho();
     // calcular límite vertical usando una estimación del alto del observador si está disponible
     try {
        // Observador no declara getAlto(), así que asumimos que el alto del espacio se pasa
        this.yMax = altoEspacio - 1; // valor por defecto; los subtipos pueden usar otra lógica
     } catch (Exception e) {
        this.yMax = altoEspacio;
     }
   }

   public int getPosicionMediaX(){
    return x + observador.getAncho()/2;
}
public int getY(){
    return y;
}
   
   public void moverDerecha() {
      this.x = Math.min(x + velocidad, xMax);
      observador.mover(this.x, y);
   }
 
   public void moverArriba() {
      this.y = Math.max(0, y - velocidad);
      observador.mover(x, this.y);
   }

   public void moverAbajo() {
      this.y = Math.min(y + velocidad, yMax);
      observador.mover(x, this.y);
   }

   public void mover(int x) {
         // mover horizontalmente manteniendo y, con límites
         if (x < 0) x = 0;
         if (x > xMax) x = xMax;
         this.x = x;
         observador.mover(this.x, this.y);
   }
   public void mover(int x, int y) {
        // Asegurar que x esté dentro de los límites
        if (x < 0) {
            x = 0;
        } else if (x > xMax) {
            x = xMax;
        }
        this.x = x;
        this.y = y;
        observador.mover(x, y);
    }
	
   
   public int getX() {
      return x;
   }

   public int getVelocidad() {
      return velocidad;
   }

   public Observador getObservador() {
      return observador;
	}

    public int getAnchoEspacio() {
		return xMax + observador.getAncho();
	}

 }



