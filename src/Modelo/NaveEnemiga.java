package Modelo;

public class NaveEnemiga extends ObjetoJuegoActualizable implements Destruible {
    private boolean moviendoDerecha = true;
    private int bajarCada = 0;
    private boolean destruido = false;
    private int movimientoHorizontal = 2;
    private static final int MOVIMIENTO_VERTICAL = 20;

    public NaveEnemiga(int x, int y, Observador observador, int anchoEspacio, int altoEspacio, int movimientoHorizontal) {
        super(x, y, movimientoHorizontal, observador, anchoEspacio, altoEspacio);
        this.movimientoHorizontal = movimientoHorizontal;
    }
    
    @Override
    public void destruir() {
        destruido = true;
        getObservador().mover(-1000, -1000); // Mueve fuera de la pantalla
    }
    
    @Override
    public boolean estaDestruido() {
        return destruido;
    }

    @Override
    public void actualizarPosicion() {
        if (destruido) return;
        
        if (bajarCada > 0) {
            mover(x, y + MOVIMIENTO_VERTICAL);
            bajarCada--;
            moviendoDerecha = !moviendoDerecha;
        } else {
            if (moviendoDerecha) {
                mover(x + movimientoHorizontal, y);
            } else {
                mover(x - movimientoHorizontal, y);
            }
        }
    }

    public void cambiarDireccion() {
        bajarCada = 1;
    }

    public boolean isMoviendoDerecha() {
        return moviendoDerecha;
    }

    public void setMovimientoHorizontal(int movimientoHorizontal) {
        this.movimientoHorizontal = movimientoHorizontal;
    }
}