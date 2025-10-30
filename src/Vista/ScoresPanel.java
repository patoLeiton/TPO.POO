package Vista;

import Modelo.Dificultad;
import Modelo.Ranking;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ScoresPanel extends JPanel {
    private Ventana ventana;
    private JComboBox<Dificultad> combo;
     private BufferedImage fondo = null;
    private JPanel listPanel;

    public ScoresPanel(Ventana ventana) {
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


    JLabel title = new JLabel("ScoreBoard");
    title.setFont(new Font("Monospaced", Font.ITALIC, 26));
    title.setBounds(320, 40, 200, 30);
    title.setForeground(java.awt.Color.WHITE);
    add(title);

    // Área donde mostraremos el ranking: panel con filas estilizadas
    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setOpaque(false);
    JScrollPane scroll = new JScrollPane(listPanel);
    // Reposicionar el scroll para que quede más arriba y centrado
    scroll.setBounds(150, 100, 500, 320);
    scroll.getViewport().setOpaque(false);
    scroll.setOpaque(false);
    add(scroll);

    // Cargar ranking y mostrarlo
    refreshRankingDisplay();

    JButton volver = new JButton("Volver al menu");
    // Mantener el botón que creaste y colocarlo más abajo y centrado
    volver.setBounds(320, 440, 160, 40);
    volver.addActionListener(e -> ventana.mostrarMenu());
    add(volver);

    volver.setBackground(new Color(0xFF5C77));
    volver.setFont(new Font("Monospaced", Font.ITALIC, 20));
    volver.setForeground(new Color(0xFFE6EA));
}

    private void refreshRankingDisplay() {
        listPanel.removeAll();
        Ranking r = new Ranking();
        List<Map.Entry<String, Integer>> list = r.getSortedDesc();
        int idx = 1;
        if (list.isEmpty()) {
            JLabel empty = new JLabel("No hay puntuaciones todavía.");
            empty.setFont(new Font("Monospaced", Font.PLAIN, 16));
            empty.setForeground(new Color(0xFFE6EA));
            empty.setBorder(new EmptyBorder(8,8,8,8));
            listPanel.add(empty);
        } else {
            for (Map.Entry<String, Integer> e : list) {
                JPanel row = new JPanel(new BorderLayout());
                row.setOpaque(true);
                row.setBackground(new Color(0xFF5C77));
                row.setMaximumSize(new Dimension(440, 40));
                row.setBorder(new EmptyBorder(8,12,8,12));

                JLabel nameLbl = new JLabel(String.format("%2d. %s", idx, e.getKey()));
                nameLbl.setFont(new Font("Monospaced", Font.BOLD, 16));
                nameLbl.setForeground(new Color(0xFFE6EA));

                JLabel scoreLbl = new JLabel(String.valueOf(e.getValue()));
                scoreLbl.setFont(new Font("Monospaced", Font.BOLD, 16));
                scoreLbl.setForeground(new Color(0xFFE6EA));

                row.add(nameLbl, BorderLayout.WEST);
                row.add(scoreLbl, BorderLayout.EAST);
                listPanel.add(row);
                listPanel.add(Box.createRigidArea(new Dimension(0,8)));
                idx++;
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
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
