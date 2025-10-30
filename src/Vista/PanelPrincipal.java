package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Modelo.Ranking;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Timer;
import javax.swing.JPanel;
import Controlador.JuegoController;
import Modelo.Dificultad;
// import javax.swing.JComboBox; // opciones manejadas desde el menú

public class PanelPrincipal extends JPanel {

    private JuegoController juegoController;
    private int ancho;
    private int alto;
    private ImagenNave imagenNave;
    private boolean juegoTerminado = false;
    private String mensajeJuego = "";
    private BufferedImage fondo = null;
    // Configuración de disparo enemigo: probabilidad por tick y disparos máximos por tick
    private double probDisparoEnemigoPorTick = 0.04; // por defecto
    private int maxDisparosEnemigosPorTick = 3;
    private Dificultad dificultad = Dificultad.CADETE;

    public PanelPrincipal(Dificultad dificultad) {
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
        // Cargar imagen de fondo si existe
        try {
            File f = new File("image/fondo2.jpg");
            if (f.exists()) {
                fondo = ImageIO.read(f);
            }
        } catch (Exception e) {
            // si falla, dejamos fondo == null y seguirá usando el color de fondo
            fondo = null;
        }
        
        // Aplicar dificultad inicial y crear las naves enemigas y escudos
        if (dificultad != null) this.dificultad = dificultad;
        aplicarDificultad();
        crearEnemigos();
        crearEscudos();

        setFocusable(true);
        TeclaListener teclaListener = new TeclaListener(juegoController);
        addKeyListener(teclaListener);
        
        intercepatMouse();
        interceptarTecla();
        simularMovimiento();
    // panel inicializado (silencioso)
    }

    // La selección de dificultad se maneja desde el menú de opciones (Ventana/OpcionesPanel)

    private void aplicarDificultad() {
        // ajustar parámetros de disparo en el panel
        probDisparoEnemigoPorTick = dificultad.getProbDisparoPorIntento();
        maxDisparosEnemigosPorTick = dificultad.getMaxDisparosPorTick();
        // ajustar velocidad de enemigos existentes y por defecto para los nuevos
        juegoController.setVelocidadEnemigos(dificultad.getMovimientoHorizontal());
    }

    private void crearEnemigos() {
        // Posicionamiento según la imagen original de Space Invaders
        int startX = 80;  
        int startY = 50;  
        int espacioX = 60; 
        int espacioY = 50; 
        
    // creando enemigos
        
        // Crear 2 filas de enemigos tipo 0 (calamares - arriba)
        for (int fila = 0; fila < 2; fila++) {
            for (int columna = 0; columna < 11; columna++) {
                ImagenEnemiga imagenEnemiga = new ImagenEnemiga(0);
                int x = startX + (columna * espacioX);
                int y = startY + (fila * espacioY);
                imagenEnemiga.setBounds(x, y, 40, 30);
                add(imagenEnemiga);
                juegoController.crearNaveEnemiga(x, y, imagenEnemiga);
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
            }
        }
    }
    
    private void crearEscudos() {
        // Posicionamiento de escudos
        int yEscudo = alto - 150; 
        int[] posicionesX = {120, 280, 440, 600}; 
        
    // creando escudos
        
        for (int i = 0; i < 4; i++) {
            ImagenEscudo imagenEscudo = new ImagenEscudo();
            imagenEscudo.setBounds(posicionesX[i], yEscudo, 60, 45);
            add(imagenEscudo);
            juegoController.crearEscudo(posicionesX[i], yEscudo, imagenEscudo);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // dibujar imagen de fondo (si existe) escalada al tamaño del panel
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }

        // Dibujar puntuación
        g.setColor(Color.magenta);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("PUNTUACIÓN: " + juegoController.getPuntuacion(), 20, 30);
        g.drawString("ENEMIGOS: " + juegoController.getEnemigosRestantes(), ancho - 200, 30);
        
        // Dibujar mensaje de juego terminado
        if (juegoTerminado) {
            g.setFont(new Font("Monospaced", Font.BOLD, 40));
            g.setColor(Color.MAGENTA);
            int x = (ancho - g.getFontMetrics().stringWidth(mensajeJuego)) / 2;
            g.drawString(mensajeJuego, x, alto / 2);
            
            g.setFont(new Font("Monospaced", Font.PLAIN, 20));
            String reinicio = "Presiona R para reiniciar";
            x = (ancho - g.getFontMetrics().stringWidth(reinicio)) / 2;
            g.drawString(reinicio, x, alto / 2 + 50);
        }
    }

    private void intercepatMouse() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!juegoTerminado) {
                    juegoController.moverNaveJugador(e.getX());
                }
            }
        });
    }

    private void interceptarTecla() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_SPACE && !juegoTerminado) {
                    // Controlar cadencia: preguntar al controlador si puede disparar
                    if (juegoController.puedeDispararJugador()) {
                        // El jugador dispara: usar proyectil gato
                        ImagenRayo imagenRayo = new ImagenRayo(true);
                        // Posicionar el rayo inicialmente en la boca de la nave
                        int startX = imagenNave.getX() + imagenNave.getAncho()/2 - imagenRayo.getAncho()/2;
                        int startY = imagenNave.getY() - imagenRayo.getAlto();
                        imagenRayo.setBounds(startX, startY, imagenRayo.getAncho(), imagenRayo.getAlto());
                        add(imagenRayo);
                        // Asegurar que el rayo esté en primer plano
                        setComponentZOrder(imagenRayo, 0);
                        imagenRayo.setVisible(true);
                        revalidate();
                        repaint();
                        // Invocar al controlador (crea el Rayo en el modelo)
                        juegoController.disparar(imagenRayo);
                        // Reaplicar posición por si el modelo la modificó
                        imagenRayo.setBounds(startX, startY, imagenRayo.getAncho(), imagenRayo.getAlto());
                        setComponentZOrder(imagenRayo, 0);
                        imagenRayo.repaint();
                        // disparo jugador añadido
                        revalidate();
                        repaint();
                    } else {
                        // Opcional: feedback auditivo o visual para indicar que está en cooldown
                        java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                } else if (evento.getKeyCode() == KeyEvent.VK_R && juegoTerminado) {
                    reiniciarJuego();
                }
            }
        });
    }

    private void simularMovimiento() {
        Timer gameLoop = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!juegoTerminado) {
                    juegoController.actualizarPosiciones();
                    // Disparos enemigos: permitir varios disparos por tick con una probabilidad por intento
                    for (int intento = 0; intento < maxDisparosEnemigosPorTick; intento++) {
                        if (Math.random() < probDisparoEnemigoPorTick) {
                            ImagenRayo imagenRayoEnemigo = new ImagenRayo(false);
                            // Añadir y poner en primer plano; el controlador establecerá la posición real
                            add(imagenRayoEnemigo);
                            setComponentZOrder(imagenRayoEnemigo, 0);
                            imagenRayoEnemigo.setVisible(true);
                            // Invocar al controlador para que seleccione un enemigo y cree el modelo del rayo
                            juegoController.dispararDesdeEnemigoAleatorio(imagenRayoEnemigo);
                            // Asegurar que el rayo enemigo quede en primer plano tras que el controlador lo posicione
                            setComponentZOrder(imagenRayoEnemigo, 0);
                            imagenRayoEnemigo.repaint();
                        }
                    }
                    
                    // Verificar victoria o derrota
                    if (juegoController.hayVictoria()) {
                        juegoTerminado = true;
                        mensajeJuego = "¡VICTORIA!";
                        handleGameOver();
                    } else if (juegoController.hayGameOver()) {
                        juegoTerminado = true;
                        mensajeJuego = "GAME OVER";
                        handleGameOver();
                    }
                }
                repaint();
            }
        });
        gameLoop.start();
    }
    
    private void reiniciarJuego() {
        // Remover todos los componentes
        removeAll();
        
        // Reiniciar variables
        juegoTerminado = false;
        mensajeJuego = "";
        
        // Recrear el juego
        int posicionNaveJugadorX = ancho / 2 - 25;
        int posicionNaveJugadorY = alto - 80;
        
        imagenNave = new ImagenNave();
        imagenNave.setBounds(posicionNaveJugadorX, posicionNaveJugadorY, 50, 30);
        add(imagenNave);

        juegoController = new JuegoController(ancho, alto, posicionNaveJugadorX, posicionNaveJugadorY, imagenNave);
        
        crearEnemigos();
        crearEscudos();
        
        revalidate();
        repaint();
        requestFocus();
    }

    private void handleGameOver() {
        // Pedimos el nombre del jugador y actualizamos el ranking
        final int puntuacion = juegoController.getPuntuacion();
        // Estamos en el EDT (Timer), así que podemos mostrar el JOptionPane directamente
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre para el ranking:", "Guardar puntuación", JOptionPane.PLAIN_MESSAGE);
        if (nombre != null) {
            nombre = nombre.trim();
            if (!nombre.isEmpty()) {
                Ranking r = new Ranking();
                r.addOrUpdate(nombre, puntuacion);
            }
        }
        // Mostrar el panel de scores
        java.awt.Window w = SwingUtilities.getWindowAncestor(PanelPrincipal.this);
        if (w instanceof Ventana) {
            ((Ventana) w).mostrarScores();
        }
    }

    
}