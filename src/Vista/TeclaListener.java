package Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class TeclaListener extends KeyAdapter {
	
@Override
public void keyPressed(KeyEvent evento) {
	
	int tecla = evento.getKeyCode();
	if (tecla == KeyEvent.VK_RIGHT) {
		
		System.out.println("tecla presionada");
	}
	
}
}
