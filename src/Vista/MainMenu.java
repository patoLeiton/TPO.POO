package Vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Dificultad;

public class MainMenu extends JPanel {
    private Ventana ventana;

    public MainMenu(Ventana ventana) {
        this.ventana = ventana;
        setLayout(null);
        setOpaque(true);
        setPreferredSize(new Dimension(800,600));
        setBackground(java.awt.Color.BLACK);
        // Título
    JLabel title = new JLabel("SPACE INVADERS - MENU");
    title.setFont(new Font("Monospaced", Font.BOLD, 28));
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
        jugar.setBackground(java.awt.Color.LIGHT_GRAY);
        opciones.setBackground(java.awt.Color.LIGHT_GRAY);
        salir.setBackground(java.awt.Color.LIGHT_GRAY);
    }
}
