package Modelo;

public class Rayo extends ObjetoJuegoActualizable implements Destruible {
    private boolean destruido = false;
    private boolean haciaArriba = true;
    
    public Rayo(int x, int y, Observador observador, int anchoEspacio, int altoEspacio, boolean haciaArriba) {
        super(x, y, 10, observador, anchoEspacio, altoEspacio);
        this.haciaArriba = haciaArriba;
    }

    public boolean isHaciaArriba() {
        return haciaArriba;
    }

    @Override
    public void actualizarPosicion() {
        if (destruido) return;
        if (haciaArriba) {
            // Si sale de la pantalla por arriba, destruir
            if (y <= 0) {
                destruir();
                return;
            }
            moverArriba();
        } else {
            // proyectil enemigo se mueve hacia abajo
            // Si sale de la pantalla por abajo, destruir
            // yMax está protegido dentro de ObjetoJuego; moverAbajo usa yMax
            if (y >= Integer.MAX_VALUE - 1000) {
                // fallback safety
                destruir();
                return;
            }
            moverAbajo();
            // Si llegó al borde inferior (aprox), destruir
            // No tenemos directamente el alto del espacio aquí, así que destrucción se limpiará por lista en Espacio
        }
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
}