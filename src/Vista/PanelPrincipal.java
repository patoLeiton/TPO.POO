package Vista;

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

		intercepatMouse();
		simularMovimiento();

		interceptarTecla();

		


		ancho = 800;
		alto = 600;
		setLayout(null);
		setPreferredSize(new Dimension(ancho,alto));


		
		imagenNave= new ImagenNave();
		add(imagenNave);
		imagenNave.mover(200,100);

		int posicionNaveJugadorX= 200;
		int posicionNaveJugadorY= 100;
		juegoController= new JuegoController(ancho,alto,posicionNaveJugadorX,posicionNaveJugadorY,imagenNave);
		
		setFocusable(true);
		TeclaListener teclaListener = new TeclaListener(juegoController);
		addKeyListener(teclaListener);
	}

private void intercepatMouse(){
	addMouseMotionListener(new MouseMotionAdapter(){
		@Override
		public void mouseMoved(MouseEvent e){
			juegoController.moverNaveJugador(e.getX());
		}
	});
}

private void interceptarTecla(){
    addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent evento) {
            if(evento.getKeyCode() == KeyEvent.VK_SPACE) {
				ImagenRayo imagenRayo = new ImagenRayo();
				add(imagenRayo);
				juegoController.disparar(imagenRayo);
			}
		}
	});

}

private void simularMovimiento(){
	Timer gameLoop = new Timer(20, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            juegoController.actualizarPosiciones();
         }
      });
      gameLoop.start();
   }
	
}






