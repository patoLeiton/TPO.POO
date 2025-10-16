package Modelo;


public class NaveJugador {
    private Observador observador;
    private int x;
    private int y;
    private int velocidad;

    public NaveJugador(int x, int y, Observador observador){
        this.x =x;
        this.y= y;
        velocidad= 10;

    }

    public void moverDerecha(){
        x+= velocidad;
        observador.mover(x, y);
    }

}
