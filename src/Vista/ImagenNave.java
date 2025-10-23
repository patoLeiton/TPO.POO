package Vista;
import java.awt.Image;

import javax.swing.ImageIcon;



public class ImagenNave extends ImagenObjetoJuego{
	
	public ImagenNave() {
		
		super(50,50);
		Image imagen= new ImageIcon("image/GatoNave.jpg").getImage();
		Image ImagenAEscala = imagen.getScaledInstance(getAncho(),getAlto(),Image.SCALE_SMOOTH);
		ImageIcon icono= new ImageIcon(ImagenAEscala);
		setIcon(icono);
		
		

	
	}
	
	
	

}
