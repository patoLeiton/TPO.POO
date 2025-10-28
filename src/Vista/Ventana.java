package Vista;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	private PanelPrincipal panelPrincipal;
	
	
	public Ventana() {
		
		panelPrincipal= new PanelPrincipal();
		setContentPane(panelPrincipal);
		setResizable(false);  // Deshabilita la capacidad de redimensionar la ventana
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		
		


	}
	
	

}
