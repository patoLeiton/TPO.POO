
package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenNave extends ImagenObjetoJuego {
    private boolean imagenCargada = false;
    
    public ImagenNave() {
        super(50, 30);
        setOpaque(false);
        cargarImagen();
    }
    
    private void cargarImagen() {
        try {
            // Intenta primero con jugador.png
            Image imagen = new ImageIcon("image/jugador.png").getImage();
            
            // Si no existe, intenta con GatoNave.jpg
            if (imagen.getWidth(null) <= 0) {
                imagen = new ImageIcon("image/GatoNave.jpg").getImage();
            }
            
            if (imagen.getWidth(null) > 0) {
                Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(imagenAEscala);
                setIcon(icono);
                imagenCargada = true;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen del jugador: " + e.getMessage());
            imagenCargada = false;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Si no se cargó la imagen, dibuja una nave verde
        if (!imagenCargada) {
            g.setColor(Color.GREEN);
            
            // Cañón central (arriba)
            g.fillRect(23, 0, 4, 8);
            
            // Cabina principal
            g.fillRect(15, 8, 20, 10);
            
            // Base/alas
            g.fillRect(0, 18, 50, 12);
        }
    }
}