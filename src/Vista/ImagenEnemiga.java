package Vista;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenEnemiga extends ImagenObjetoJuego {
    
    public ImagenEnemiga(int tipo) {
        super(40, 30);
        cargarImagen(tipo);
    }
    
    private void cargarImagen(int tipo) {
        try {
            String rutaImagen;
            if (tipo == 0) {
                // Enemigo tipo 1 (arriba - calamares)
                rutaImagen = "image/enemigo1.png";
            } else if (tipo == 1) {
                // Enemigo tipo 2 (medio - cangrejos)
                rutaImagen = "image/enemigo2.png";
            } else {
                // Enemigo tipo 3 (abajo - pulpos)
                rutaImagen = "image/enemigo3.png";
            }
            
            Image imagen = new ImageIcon(rutaImagen).getImage();
            Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
            ImageIcon icono = new ImageIcon(imagenAEscala);
            setIcon(icono);
            
        } catch (Exception e) {
            System.err.println("Error al cargar imagen enemiga: " + e.getMessage());
        }
    }
}
