package Modelo;


public class NaveJugador extends ObjetoJuego {
   


    public NaveJugador(int x, int y, Observador observador, int anchoEspacio){
        super(x, y,10, observador, anchoEspacio);
    }

    public Rayo disparar(Observador observadorDisparo){
        int posicionX= getPosicionMediaX();
        Rayo disparo= new Rayo(posicionX, getY(), observadorDisparo, getAnchoEspacio() );
        return disparo;
    }
}
