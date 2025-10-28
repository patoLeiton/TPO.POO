package Vista;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenEscudo extends ImagenObjetoJuego {
    
    public ImagenEscudo() {
        super(60, 45);
        cargarImagen();
    }
    
    private void cargarImagen() {
        try {
            Image imagen = new ImageIcon("image/escudo.png").getImage();
            Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
            ImageIcon icono = new ImageIcon(imagenAEscala);
            setIcon(icono);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen del escudo: " + e.getMessage());
        }
    }
}
