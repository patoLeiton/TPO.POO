package Modelo;


public abstract class ObjetoJuego{
   protected int x;
   protected int y;
   private int velocidad;
   private Observador observador;
   	private int xMax;
   

   public ObjetoJuego(int x, int y, int velocidad, Observador observador, int anchoEspacio) {
    super();  
      this.x = x;
      this.y = y;
      this.velocidad = velocidad;
      this.observador = observador;
      observador.mover(x,y);
      this.xMax = anchoEspacio - observador.getAncho();
   }

   public int getPosicionMediaX(){
    return x + observador.getAncho()/2;
}
public int getY(){
    return y;
}
   
   public void moverDerecha() {
      observador.mover(x + velocidad, y);
   }
 
   public void moverArriba() {
      observador.mover(x, y - velocidad);
   }

   public void mover(int x) {
      observador.mover(x, y);
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



