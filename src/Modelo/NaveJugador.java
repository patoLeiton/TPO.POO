package Modelo;


public class NaveJugador {
    private Observador observador;
    private int x;
    private int y;
    private int velocidad;
    private int xMax;


    public NaveJugador(int x, int y, Observador observador, int anchoEspacio){
        this.x =x;
        this.y= y;
        velocidad= 10;
        this.observador= observador;
        observador.mover(x, y);
        this.xMax= anchoEspacio-observador.getAncho();

    }

    public void moverDerecha(){
        if(x + velocidad< xMax){
          
        x+= velocidad;
        observador.mover(x, y);
    }
}

    public void mover(int x){
        this.x=x;
        observador.mover(x,y);
    }

}
