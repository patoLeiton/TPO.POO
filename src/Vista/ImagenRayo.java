package Vista;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagenRayo extends ImagenObjetoJuego{
    
    public ImagenRayo(){
        this(false);
    }

    /**
     * Crear una imagen de rayo.
     * @param esJugador true para proyectil del jugador (gato), false para proyectil enemigo (pez)
     */
    public ImagenRayo(boolean esJugador){
        // Aumentar el tamaño de los proyectiles para mejorar visibilidad en pantalla
        // Jugador: ligeramente más grande; Enemigo: también aumentado para ser visible sobre fondos variados
        super(esJugador ? 18 : 16, esJugador ? 54 : 48);
        String ruta = esJugador ? "image/proyectil gato.png" : "image/proyectil pez.png";
        try {
            // Cargar la imagen con ImageIO para asegurarnos que está completamente cargada
            BufferedImage original = ImageIO.read(new File(ruta));
            if (original != null) {
                BufferedImage scaledOrig = new BufferedImage(getAncho(), getAlto(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = scaledOrig.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawImage(original, 0, 0, getAncho(), getAlto(), null);
                g2.dispose();
                BufferedImage finalImg = scaledOrig;
                // Para proyectiles enemigos, añadir un pequeño resplandor semitransparente detrás para mejorar visibilidad
                if (!esJugador) {
                    try {
                        BufferedImage composed = new BufferedImage(getAncho(), getAlto(), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g3 = composed.createGraphics();
                        g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        g3.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        // dibujar un resplandor oval semitransparente centrado
                        java.awt.Color glow = new java.awt.Color(255, 220, 0, 80);
                        int pad = Math.max(2, getAncho() / 6);
                        g3.setColor(glow);
                        g3.fillOval(-pad/2, -pad, getAncho()+pad, getAlto()+pad);
                        // dibujar la imagen encima
                        g3.drawImage(scaledOrig, 0, 0, null);
                        g3.dispose();
                        finalImg = composed;
                    } catch (Exception ex) {
                        // si falla, usar la imagen escalada original
                        finalImg = scaledOrig;
                    }
                }
                // Usar icon transparente (si el PNG tiene alpha) y no pintar fondo del componente
                setIcon(new ImageIcon(finalImg));
                setOpaque(false); // permitir transparencia del PNG
                return;
            }
        } catch (Exception e) {
            // Ignorar y usar fallback
        }

        // Fallback si la imagen no se pudo cargar: crear icon transparente con forma simple
        try {
            BufferedImage fallback = new BufferedImage(getAncho(), getAlto(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = fallback.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            java.awt.Color fill = esJugador ? new java.awt.Color(255, 100, 100, 200) : new java.awt.Color(100, 180, 255, 200);
            g.setColor(fill);
            g.fillOval(0, 0, getAncho(), getAlto());
            g.dispose();
            setIcon(new ImageIcon(fallback));
            setOpaque(false);
        } catch (Exception e) {
            // último recurso: sin icon, solo borde
            setOpaque(false);
            // sin borde
        }
    }
}
