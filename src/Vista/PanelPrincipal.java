package Vista;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;



public class PanelPrincipal extends JPanel {

	private JuegoController juegoController;
	
	private int ancho;
	private int alto;
	private ImagenNave imagenNave;

	
	
	
	public PanelPrincipal() {
		
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
		//TeclaListener teclaListener = new TeclaListener();
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


}


