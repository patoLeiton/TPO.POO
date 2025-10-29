package Vista;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;








import Modelo.Dificultad;

public class MainMenu extends JPanel {
    private Ventana ventana;
    private BufferedImage fondo = null;

    public MainMenu(Ventana ventana) {
        this.ventana = ventana;
        setLayout(null);
        setOpaque(true);
        setPreferredSize(new Dimension(800,600));
        setBackground(java.awt.Color.MAGENTA);

        try {
            File f = new File("image/BackgroundMenu.jpg");
            if (f.exists()) {
                fondo = ImageIO.read(f);
            }
        } catch (Exception e) {
            // si falla, dejamos fondo == null y seguirá usando el color de fondo
            fondo = null;
        }

        // Título
    JLabel title = new JLabel("SPACE INVADERS - MENU");
    title.setFont(new Font("Monospaced", Font.ITALIC , 28));
    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    // centrar el título en el panel (ancho 800 -> centro 400)
    title.setBounds(200, 80, 400, 40);
    title.setForeground(java.awt.Color.WHITE);
        add(title);

        JButton jugar = new JButton("Jugar");
        jugar.setBounds(320, 180, 160, 40);
        jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Iniciar juego con la dificultad actual de la ventana
                Dificultad d = ventana.getDificultadActual();
                ventana.mostrarJuego(d);
            }
        });
        add(jugar);

        JButton opciones = new JButton("Opciones");
        opciones.setBounds(320, 240, 160, 40);
        opciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.mostrarOpciones();
            }
        });
        add(opciones);

        JButton salir = new JButton("Salir");
        salir.setBounds(320, 300, 160, 40);
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(salir);
        // Hacer botones más visibles en fondo oscuro
        jugar.setBackground( new Color(0xFF5C77));
        opciones.setBackground( new Color(0xFF5C77));
        salir.setBackground( new Color(0xFF5C77));

        jugar.setFont(new Font("Monospaced", Font.ITALIC , 20));
        opciones.setFont(new Font("Monospaced", Font.ITALIC , 20));
        salir.setFont(new Font("Monospaced", Font.ITALIC , 20 ));

        jugar.setForeground(new Color(0xFFE6EA));
        opciones.setForeground(new Color(0xFFE6EA));
        salir.setForeground(new Color(0xFFE6EA));


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // dibujar imagen de fondo (si existe) escalada al tamaño del panel
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
}
}