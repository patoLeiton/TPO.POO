package Vista;

import javax.swing.JLabel;
import Modelo.Observador;

public abstract class ImagenObjetoJuego extends JLabel implements Observador {
    private int ancho;
    private int alto;

    public ImagenObjetoJuego(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        setSize(ancho, alto);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    @Override
    public void mover(int x, int y) {
        setBounds(x, y, ancho, alto);
        setLocation(x, y);
        // mover sin prints de depuración
    }
}