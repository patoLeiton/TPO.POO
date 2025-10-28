package Modelo;

public class Escudo extends ObjetoJuego implements Destruible {
    private int vida;
    private static final int VIDA_MAXIMA = 100;
    private static final int DANIO_POR_IMPACTO = 25;
    
    public Escudo(int x, int y, Observador observador, int anchoEspacio) {
        super(x, y, 0, observador, anchoEspacio);
        this.vida = VIDA_MAXIMA;
    }
    
    public void recibirDanio() {
        vida -= DANIO_POR_IMPACTO;
        if (vida <= 0) {
            vida = 0;
            destruir();
        }
        // Notificar al observador sobre el cambio de vida
        if (observador instanceof EscudoObservador) {
            ((EscudoObservador) observador).actualizarVida(vida, VIDA_MAXIMA);
        }
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