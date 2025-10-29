package Vista;

import javax.swing.JFrame;
import Modelo.Dificultad;

public class Ventana extends JFrame{

	private Dificultad dificultadActual = Dificultad.CADETE;

	public Ventana() {
		// Mostrar menu principal al iniciar
		setResizable(false);  // Deshabilita la capacidad de redimensionar la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mostrarMenu();
		setVisible(true);
	}

	public void mostrarMenu() {
		MainMenu menu = new MainMenu(this);
		setContentPane(menu);
		pack();
		setLocationRelativeTo(null);
		revalidate();
	}

	public void mostrarJuego(Dificultad dificultad) {
		this.dificultadActual = dificultad;
		PanelPrincipal panelPrincipal = new PanelPrincipal(dificultad);
		setContentPane(panelPrincipal);
		pack();
		setLocationRelativeTo(null);
		panelPrincipal.requestFocusInWindow();
		revalidate();
	}

	public void mostrarOpciones() {
		OpcionesPanel opciones = new OpcionesPanel(this);
		setContentPane(opciones);
		pack();
		setLocationRelativeTo(null);
		revalidate();
	}

	public Dificultad getDificultadActual() {
		return dificultadActual;
	}

	public void setDificultadActual(Dificultad d) {
		this.dificultadActual = d;
	}

}
