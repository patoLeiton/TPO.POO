package Vista;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.Timer;


import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controlador.JuegoController;
import Vista.TeclaListener;





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
		juegoController= new JuegoController(ancho,alto,posicionNaveJugadorX, posicionNaveJugadorY,imagenNave);
		
		setFocusable(true);
		TeclaListener teclaListener = new TeclaListener(imagenNave);
		
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

	setFocusable(true);
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent evento){

			int tecla= evento.getKeyCode();
			if(tecla ==KeyEvent.VK_RIGHT){
				juegoController.moverNaveJugadorDerecha();
			} else if(tecla==KeyEvent.VK_SPACE){
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






