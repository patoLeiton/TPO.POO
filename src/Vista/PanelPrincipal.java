package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

        // Crear la nave del jugador primero
        int posicionNaveJugadorX = ancho / 2 - 25;
        int posicionNaveJugadorY = alto - 80;
        
        imagenNave = new ImagenNave();
        imagenNave.setBounds(posicionNaveJugadorX, posicionNaveJugadorY, 50, 30);
        add(imagenNave);

        // Inicializar el controlador
        juegoController = new JuegoController(ancho, alto, posicionNaveJugadorX, posicionNaveJugadorY, imagenNave);
        
        // Crear las naves enemigas y escudos
        crearEnemigos();
        crearEscudos();

        setFocusable(true);
        TeclaListener teclaListener = new TeclaListener(juegoController);
        addKeyListener(teclaListener);
        
        intercepatMouse();
        interceptarTecla();
        simularMovimiento();
        
        System.out.println("Panel inicializado. Componentes: " + getComponentCount());
    }

    private void crearEnemigos() {
        // Posicionamiento según la imagen original de Space Invaders
        int startX = 80;  
        int startY = 50;  
        int espacioX = 60; 
        int espacioY = 50; 
        
        System.out.println("Creando enemigos...");
        
        // Crear 2 filas de enemigos tipo 0 (calamares - arriba)
        for (int fila = 0; fila < 2; fila++) {
            for (int columna = 0; columna < 11; columna++) {
                ImagenEnemiga imagenEnemiga = new ImagenEnemiga(0);
                int x = startX + (columna * espacioX);
                int y = startY + (fila * espacioY);
                imagenEnemiga.setBounds(x, y, 40, 30);
                add(imagenEnemiga);
                juegoController.crearNaveEnemiga(x, y, imagenEnemiga);
                System.out.println("Enemigo creado en: " + x + ", " + y);
            }
        }
        
        // Crear 2 filas de enemigos tipo 1 (cangrejos - medio)
        for (int fila = 2; fila < 4; fila++) {
            for (int columna = 0; columna < 11; columna++) {
                ImagenEnemiga imagenEnemiga = new ImagenEnemiga(1);
                int x = startX + (columna * espacioX);
                int y = startY + (fila * espacioY);
                imagenEnemiga.setBounds(x, y, 40, 30);
                add(imagenEnemiga);
                juegoController.crearNaveEnemiga(x, y, imagenEnemiga);
                System.out.println("Enemigo creado en: " + x + ", " + y);
            }
        }
    }
    
    private void crearEscudos() {
        // Posicionamiento de escudos
        int yEscudo = alto - 150; 
        int[] posicionesX = {120, 280, 440, 600}; 
        
        System.out.println("Creando escudos...");
        
        for (int i = 0; i < 4; i++) {
            ImagenEscudo imagenEscudo = new ImagenEscudo();
            imagenEscudo.setBounds(posicionesX[i], yEscudo, 60, 45);
            add(imagenEscudo);
            System.out.println("Escudo creado en: " + posicionesX[i] + ", " + yEscudo);
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