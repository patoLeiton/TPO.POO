package Controlador;

import Modelo.Escudo;
import Modelo.Espacio;
import Modelo.NaveEnemiga;
import Modelo.NaveJugador;
import Modelo.Observador;
import Modelo.Rayo;

public class JuegoController {
    private Espacio espacio;

    public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave) {
        espacio = new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNave);
    }

    public void crearNaveEnemiga(int x, int y, Observador observador) {
        NaveEnemiga enemiga = new NaveEnemiga(x, y, observador, espacio.getNaveJugador().getAnchoEspacio());
        espacio.agregarNaveEnemiga(enemiga);
    }
    
    public void crearEscudo(int x, int y, Observador observador) {
        Escudo escudo = new Escudo(x, y, observador, espacio.getNaveJugador().getAnchoEspacio());
        espacio.agregarEscudo(escudo);
    }

    public void moverNaveJugadorDerecha() {
        NaveJugador nave = espacio.getNaveJugador();
        nave.mover(nave.getX() + nave.getVelocidad(), nave.getY());
    }

    public void moverNaveJugadorIzquierda() {
        NaveJugador nave = espacio.getNaveJugador();
        nave.mover(nave.getX() - nave.getVelocidad(), nave.getY());
    }

    public void moverNaveJugador(int x) {
        NaveJugador naveJugador = espacio.getNaveJugador();
        naveJugador.mover(x, naveJugador.getY());
    }

    public void disparar(Observador observador) {
        NaveJugador naveJugador = espacio.getNaveJugador();
        Rayo disparo = naveJugador.disparar(observador);
        espacio.agregar(disparo);
    }

    public void actualizarPosiciones() {
        espacio.actualizarPosiciones();
    }
    
    public int getPuntuacion() {
        return espacio.getPuntuacion();
    }
    
    public int getEnemigosRestantes() {
        return espacio.getEnemigosRestantes();
    }
    
    public boolean hayVictoria() {
        return espacio.hayVictoria();
    }
    
    public boolean hayGameOver() {
        return espacio.hayGameOver();
    }
}