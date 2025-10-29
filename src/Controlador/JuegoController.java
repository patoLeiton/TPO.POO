package Controlador;

import Modelo.Escudo;
import Modelo.Espacio;
import Modelo.NaveEnemiga;
import Modelo.NaveJugador;
import Modelo.Observador;
import Modelo.Rayo;
import Vista.ImagenObjetoJuego;

public class JuegoController {
    private Espacio espacio;
    // Control de cadencia de disparo del jugador (ms)
    private long ultimoDisparoJugadorTs = 0L;
    private final int cadenciaDisparoMs = 300; // 300ms entre disparos

    public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave) {
        espacio = new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNave);
    }

    public void crearNaveEnemiga(int x, int y, Observador observador) {
        // por defecto usar movimiento horizontal estándar; el panel puede llamar a la sobrecarga con velocidad
        NaveEnemiga enemiga = new NaveEnemiga(x, y, observador, espacio.getAncho(), espacio.getAlto(), 2);
        espacio.agregarNaveEnemiga(enemiga);
    }

    // Sobrecarga que permite especificar velocidad horizontal de la nave enemiga
    public void crearNaveEnemiga(int x, int y, Observador observador, int movimientoHorizontal) {
        NaveEnemiga enemiga = new NaveEnemiga(x, y, observador, espacio.getAncho(), espacio.getAlto(), movimientoHorizontal);
        espacio.agregarNaveEnemiga(enemiga);
    }

    /**
     * Actualiza la velocidad horizontal de todas las naves enemigas existentes.
     */
    public void setVelocidadEnemigos(int movimientoHorizontal) {
        espacio.setMovimientoHorizontalParaTodasNaves(movimientoHorizontal);
    }
    
    public void crearEscudo(int x, int y, Observador observador) {
        Escudo escudo = new Escudo(x, y, observador, espacio.getAncho(), espacio.getAlto());
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
        // disparo del jugador: crear Rayo con dirección hacia arriba
    // Posición inicial del modelo (será reajustada visualmente después)
    int inicioX = naveJugador.getX();
    int inicioY = naveJugador.getY();
        Rayo disparo = new Rayo(inicioX, inicioY, observador, espacio.getAncho(), espacio.getAlto(), true);
        espacio.agregar(disparo);
        // Reajustar la posición visual del Observador para que el rayo aparezca centrado sobre la nave
        try {
            int centroNaveX = naveJugador.getX() + (naveJugador.getObservador().getAncho() / 2);
            int posX = centroNaveX - (observador.getAncho() / 2);
            int posY = naveJugador.getY() - (((ImagenObjetoJuego) observador).getAlto());
            observador.mover(posX, posY);
        } catch (Exception e) {
            // ignore reposition errors
        }
    }

    public boolean puedeDispararJugador() {
        long ahora = System.currentTimeMillis();
        if (ahora - ultimoDisparoJugadorTs >= cadenciaDisparoMs) {
            ultimoDisparoJugadorTs = ahora;
            return true;
        }
        return false;
    }

    public void dispararDesdeEnemigoAleatorio(Observador observador) {
        NaveEnemiga enemigo = espacio.obtenerEnemigoAleatorioActivo();
        if (enemigo == null) return;
        int posX = enemigo.getPosicionMediaX();
        int posY = enemigo.getY() + 30; // iniciar un poco por debajo de la nave enemiga
        Rayo disparo = new Rayo(posX, posY, observador, espacio.getAncho(), espacio.getAlto(), false);
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