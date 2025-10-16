package Vista;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImagenNave extends JLabel {
	
	private int ancho;
	private int alto;
	
	public ImagenNave() {
		ancho= 50;
		alto= 50;
		Image imagen= new ImageIcon("image/GatoNave.jpg").getImage();
		Image ImagenAEscala = imagen.getScaledInstance(ancho, alto,Image.SCALE_SMOOTH);
		ImageIcon icono= new ImageIcon(ImagenAEscala);
		setIcon(icono);
		
		

	
	}
	
	public void mover(int x, int y) {
		
		setBounds(x, y, ancho, alto);
		
	}
	

}
