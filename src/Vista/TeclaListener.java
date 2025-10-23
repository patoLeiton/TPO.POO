package Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class TeclaListener extends KeyAdapter {
	private ImagenNave imagenNave;

	public TeclaListener(ImagenNave imagenNave){
		this.imagenNave= imagenNave;
	}

@Override
public void keyPressed(KeyEvent evento) {
	
	int tecla = evento.getKeyCode();
	if (tecla == KeyEvent.VK_RIGHT) {
		imagenNave.mover(600,400);

	}
	
}
}
