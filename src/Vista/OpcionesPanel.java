package Vista;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Dificultad;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class OpcionesPanel extends JPanel {
    private Ventana ventana;
    private JComboBox<Dificultad> combo;
     private BufferedImage fondo = null;

    public OpcionesPanel(Ventana ventana) {
        this.ventana = ventana;
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        setBackground(java.awt.Color.PINK);

        try {
            File f = new File("image/BackgroundMenu.jpg");
            if (f.exists()) {
                fondo = ImageIO.read(f);
            }
        } catch (Exception e) {
            // si falla, dejamos fondo == null y seguirá usando el color de fondo
            fondo = null;
        }


    JLabel title = new JLabel("OPCIONES");
    title.setFont(new Font("Monospaced", Font.ITALIC, 26));
    title.setBounds(320, 40, 200, 30);
    title.setForeground(java.awt.Color.WHITE);
    add(title);

    combo = new JComboBox<>(Dificultad.values());
        combo.setBounds(300, 100, 200, 30);
        combo.setSelectedItem(ventana.getDificultadActual());
        add(combo);

    JButton aplicar = new JButton("Aplicar");
        aplicar.setBounds(300, 150, 90, 30);
        aplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dificultad sel = (Dificultad) combo.getSelectedItem();
                if (sel != null) {
                    ventana.setDificultadActual(sel);
                }
                ventana.mostrarMenu();
            }
        });
        add(aplicar);

        JButton volver = new JButton("Volver");
        volver.setBounds(410, 150, 90, 30);
        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.mostrarMenu();
            }
        });
        add(volver);
    combo.setBackground(new Color(0xFF5C77));
    combo.setFont(new Font("Monospaced", Font.ITALIC, 20));
    combo.setForeground(new Color(0xFFE6EA));
    aplicar.setForeground(new Color(0xFFE6EA));
    aplicar.setBackground(new Color(0xFF5C77));
    aplicar.setFont(new Font("Monospaced", Font.ITALIC, 13));
    volver.setForeground(new Color(0xFFE6EA));
    volver.setBackground(new Color(0xFF5C77));
    volver.setFont(new Font("Monospaced", Font.ITALIC, 15));
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
