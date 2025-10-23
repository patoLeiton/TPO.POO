package Vista;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;




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

		
<<<<<<< Updated upstream


=======
		
>>>>>>> Stashed changes
		ancho = 800;
		alto = 600;
		setLayout(null);
		setPreferredSize(new Dimension(ancho,alto));


		
		imagenNave= new ImagenNave();
		add(imagenNave);
		imagenNave.mover(400,300);

		int posicionNaveJugadorX= 400;
		int posicionNaveJugadorY= 300;
		juegoController= new JuegoController(ancho,alto,posicionNaveJugadorX,posicionNaveJugadorY,imagenNave);
		
		setFocusable(true);
		TeclaListener teclaListener = new TeclaListener(imagenNave);
		addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent evento){

			int tecla= evento.getKeyCode();
			if(tecla ==KeyEvent.VK_RIGHT){
				juegoController.moverNaveJugadorDerecha();

			}
		}
	});

	
	

}



private void intercepatMouse(){
	addMouseMotionListener(new MouseMotionAdapter(){

		@Override
		public void mouseMoved(MouseEvent e){
			juegoController.moverNaveJugador(e.getX());
		}
	});


}



}


