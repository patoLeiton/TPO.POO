package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenEnemiga extends ImagenObjetoJuego {
    private boolean imagenCargada = false;
    private int tipo;
    
    public ImagenEnemiga(int tipo) {
        super(40, 30);
        this.tipo = tipo;
        setOpaque(false);
        cargarImagen(tipo);
    }
    
    private void cargarImagen(int tipo) {
        try {
            String rutaImagen;
            if (tipo == 0) {
                rutaImagen = "image/enemigo1.png";
            } else if (tipo == 1) {
                rutaImagen = "image/enemigo2.png";
            } else {
                rutaImagen = "image/enemigo3.png";
            }
            
            Image imagen = new ImageIcon(rutaImagen).getImage();
            if (imagen.getWidth(null) > 0) {
                Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(imagenAEscala);
                setIcon(icono);
                imagenCargada = true;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen enemiga tipo " + tipo + ": " + e.getMessage());
            imagenCargada = false;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Si no se cargó la imagen, dibuja un rectángulo de color
        if (!imagenCargada) {
            g.setColor(Color.WHITE);
            g.fillRect(5, 5, getAncho() - 10, getAlto() - 10);
            
            // Dibuja "ojos" para distinguirlos
            g.setColor(Color.BLACK);
            g.fillRect(10, 10, 8, 8);
            g.fillRect(22, 10, 8, 8);
        }
    }
}