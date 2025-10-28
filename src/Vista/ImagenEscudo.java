package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import Modelo.EscudoObservador;

public class ImagenEscudo extends ImagenObjetoJuego implements EscudoObservador {
    private boolean imagenCargada = false;
    private float porcentajeVida = 1.0f;
    
    public ImagenEscudo() {
        super(60, 45);
        setOpaque(false);
        cargarImagen();
    }
    
    private void cargarImagen() {
        try {
            Image imagen = new ImageIcon("image/escudo.png").getImage();
            if (imagen.getWidth(null) > 0) {
                Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(imagenAEscala);
                setIcon(icono);
                imagenCargada = true;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen del escudo: " + e.getMessage());
            imagenCargada = false;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Si no se cargó la imagen, dibuja un escudo verde
        if (!imagenCargada) {
            g.setColor(Color.GREEN);
            
            // Forma de escudo/bunker
            g.fillRect(5, 10, 50, 30);
            g.fillRect(10, 5, 40, 10);
            
            // Recortes inferiores para dar forma
            g.setColor(Color.BLACK);
            g.fillRect(15, 30, 10, 10);
            g.fillRect(35, 30, 10, 10);
        }
    }
}