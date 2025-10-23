package Controlador;

import Modelo.Espacio;
import Modelo.NaveJugador;
import Modelo.Observador;



public class JuegoController {
    private Espacio espacio;

    public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave){
        espacio= new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNave);

    }

    public void moverNaveJugadorDerecha(){
        NaveJugador nave = espacio.getNaveJugador();
        nave.moverDerecha();

    }

    public void moverNaveJugador(int x){
        NaveJugador naveJugador = espacio.getNaveJugador();
        naveJugador.mover(x);
    }

}
    




