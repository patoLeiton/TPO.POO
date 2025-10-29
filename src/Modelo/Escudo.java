package Modelo;

public class Escudo extends ObjetoJuego implements Destruible {
    private int vida;
    // Ajustes: queremos que el escudo soporte 20 disparos enemigos y 10 disparos del jugador.
    // Usamos VIDA_MAXIMA = 200: cada impacto enemigo quita 10 (200/20), cada impacto jugador quita 20 (200/10).
    private static final int VIDA_MAXIMA = 200;
    private static final int DANIO_POR_IMPACTO_ENEMIGO = 10;
    private static final int DANIO_POR_IMPACTO_JUGADOR = 20;
    
    public Escudo(int x, int y, Observador observador, int anchoEspacio, int altoEspacio) {
        super(x, y, 0, observador, anchoEspacio, altoEspacio);
        this.vida = VIDA_MAXIMA;
    }
    
    /**
     * Recibir daño genérico (cantidad en "puntos de vida").
     */
    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida <= 0) {
            vida = 0;
            destruir();
        }
        // Notificar al observador sobre el cambio de vida
        if (getObservador() instanceof EscudoObservador) {
            ((EscudoObservador) getObservador()).actualizarVida(vida, VIDA_MAXIMA);
        }
    }

    /**
     * Conveniencia: recibir daño por impacto de enemigo.
     */
    public void recibirDanioPorImpactoEnemigo() {
        recibirDanio(DANIO_POR_IMPACTO_ENEMIGO);
    }

    /**
     * Conveniencia: recibir daño por impacto del jugador.
     */
    public void recibirDanioPorImpactoJugador() {
        recibirDanio(DANIO_POR_IMPACTO_JUGADOR);
    }
    
    @Override
    public void destruir() {
        vida = 0;
        getObservador().mover(-1000, -1000);
    }
    
    @Override
    public boolean estaDestruido() {
        return vida <= 0;
    }
    
    public int getVida() {
        return vida;
    }
    
    public float getPorcentajeVida() {
        return (float) vida / VIDA_MAXIMA;
    }
}