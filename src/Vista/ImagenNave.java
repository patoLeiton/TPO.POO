package Vista;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Modelo.Observador;


public class ImagenNave extends JLabel implements Observador {
	
	private int ancho;
	private int alto;

	public int getAncho(){
		return ancho;
	}
	
    public void mover(int x, int y) {
		
		setBounds(x, y, ancho, alto);
		
	}

	public ImagenNave() {
		ancho= 50;
		alto= 50;
		Image imagen= new ImageIcon("image/GatoNave.jpg").getImage();
		Image ImagenAEscala = imagen.getScaledInstance(ancho, alto,Image.SCALE_SMOOTH);
		ImageIcon icono= new ImageIcon(ImagenAEscala);
		setIcon(icono);
		
		

	
	}
	
	
	

}
