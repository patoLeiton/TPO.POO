package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Timer;
import javax.swing.JPanel;
import Controlador.JuegoController;

public class PanelPrincipal extends JPanel {

    private JuegoController juegoController;
    private int ancho;
    private int alto;
    private ImagenNave imagenNave;

    public PanelPrincipal() {
        ancho = 800;
        alto = 600;
        setLayout(null);
        setPreferredSize(new Dimension(ancho, alto));
        setBackground(Color.BLACK);

        // Crear la nave del jugador
        imagenNave = new ImagenNave();
        add(imagenNave);
        
        int posicionNaveJugadorX = ancho / 2 - 25;
        int posicionNaveJugadorY = alto - 100; // Más cerca del borde inferior
        imagenNave.mover(posicionNaveJugadorX, posicionNaveJugadorY);

        juegoController = new JuegoController(ancho, alto, posicionNaveJugadorX, posicionNaveJugadorY, imagenNave);
        
        // Crear las naves enemigas
        crearEnemigos();

        setFocusable(true);
        TeclaListener teclaListener = new TeclaListener(juegoController);
        addKeyListener(teclaListener);
        
        intercepatMouse();
        interceptarTecla();
        simularMovimiento();
    }

    private void crearEnemigos() {
        // Posicionamiento según la imagen original de Space Invaders
        int startX = 50;  // Más cerca del borde izquierdo
        int startY = 30;  // Más arriba
        int espacioX = 55; // Espacio horizontal entre enemigos
        int espacioY = 45; // Espacio vertical entre filas
        
        // Crear 2 filas de enemigos tipo 0 (calamares - arriba)
        for (int fila = 0; fila < 2; fila++) {
            for (int columna = 0; columna < 11; columna++) { // 11 columnas como en la imagen
                ImagenEnemiga imagenEnemiga = new ImagenEnemiga(0);
                add(imagenEnemiga);
                int x = startX + (columna * espacioX);
                int y = startY + (fila * espacioY);
                imagenEnemiga.mover(x, y);
                juegoController.crearNaveEnemiga(x, y, imagenEnemiga);
            }
        }
        
        // Crear 2 filas de enemigos tipo 1 (cangrejos - medio)
        for (int fila = 2; fila < 4; fila++) {
            for (int columna = 0; columna < 11; columna++) { // 11 columnas como en la imagen
                ImagenEnemiga imagenEnemiga = new ImagenEnemiga(1);
                add(imagenEnemiga);
                int x = startX + (columna * espacioX);
                int y = startY + (fila * espacioY);
                imagenEnemiga.mover(x, y);
                juegoController.crearNaveEnemiga(x, y, imagenEnemiga);
            }
        }
        
        // Crear escudos
        crearEscudos();
    }
    
    private void crearEscudos() {
        // Posicionamiento de escudos como en la imagen original
        int yEscudo = alto - 150; // Posición vertical de los escudos
        int[] posicionesX = {100, 250, 480, 630}; // Posiciones horizontales específicas
        
        for (int i = 0; i < 4; i++) {
            ImagenEscudo imagenEscudo = new ImagenEscudo();
            add(imagenEscudo);
            imagenEscudo.mover(posicionesX[i], yEscudo);
        }
    }

    private void intercepatMouse() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                juegoController.moverNaveJugador(e.getX());
            }
        });
    }

    private void interceptarTecla() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_SPACE) {
                    ImagenRayo imagenRayo = new ImagenRayo();
                    add(imagenRayo);
                    juegoController.disparar(imagenRayo);
                    repaint();
                }
            }
        });
    }

    private void simularMovimiento() {
        Timer gameLoop = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juegoController.actualizarPosiciones();
                repaint();
            }
        });
        gameLoop.start();
    }
}