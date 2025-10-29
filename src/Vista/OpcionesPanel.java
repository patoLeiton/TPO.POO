package Vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Dificultad;

public class OpcionesPanel extends JPanel {
    private Ventana ventana;
    private JComboBox<Dificultad> combo;

    public OpcionesPanel(Ventana ventana) {
        this.ventana = ventana;
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        setBackground(java.awt.Color.DARK_GRAY);

    JLabel title = new JLabel("OPCIONES");
    title.setFont(new Font("Monospaced", Font.BOLD, 26));
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
    aplicar.setBackground(java.awt.Color.LIGHT_GRAY);
    volver.setBackground(java.awt.Color.LIGHT_GRAY);
    }
}
