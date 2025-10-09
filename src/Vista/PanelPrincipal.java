package Vista;

import java.awt.Dimension;
import javax.swing.JPanel;



public class PanelPrincipal extends JPanel {
	
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
		
		setFocusable(true);
		TeclaListener teclaListener = new TeclaListener();
		addKeyListener(teclaListener);
		
	}
	

}
