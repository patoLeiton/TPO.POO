package Modelo;

public class Escudo extends ObjetoJuego {
    private int vida;
    
    public Escudo(int x, int y, Observador observador, int anchoEspacio) {
        super(x, y, 0, observador, anchoEspacio);
        this.vida = 100;
    }
    
    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) {
            vida = 0;
        }
    }
    
    public boolean estaDestruido() {
        return vida <= 0;
    }
    
    public int getVida() {
        return vida;
    }
}
