package Modelo;

public class NaveEnemiga extends ObjetoJuegoActualizable implements Destruible {
    private boolean moviendoDerecha = true;
    private int bajarCada = 0;
    private boolean destruido = false;
    private static final int MOVIMIENTO_HORIZONTAL = 2;
    private static final int MOVIMIENTO_VERTICAL = 20;

    public NaveEnemiga(int x, int y, Observador observador, int anchoEspacio) {
        super(x, y, 2, observador, anchoEspacio);
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
                mover(x + MOVIMIENTO_HORIZONTAL, y);
            } else {
                mover(x - MOVIMIENTO_HORIZONTAL, y);
            }
        }
    }

    public void cambiarDireccion() {
        bajarCada = 1;
    }

    public boolean isMoviendoDerecha() {
        return moviendoDerecha;
    }
}