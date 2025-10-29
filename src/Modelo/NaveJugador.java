package Modelo;


public class NaveJugador extends ObjetoJuego {
   


    public NaveJugador(int x, int y, Observador observador, int anchoEspacio, int altoEspacio){
        super(x, y,10, observador, anchoEspacio, altoEspacio);
    }

    public Rayo disparar(Observador observadorDisparo){
        int posicionX= getPosicionMediaX();
        Rayo disparo= new Rayo(posicionX, getY(), observadorDisparo, getAnchoEspacio(), /*altoEspacio*/ 600, true );
        return disparo;
    }
}
