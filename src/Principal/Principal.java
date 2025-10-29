package Principal;

import javax.swing.SwingUtilities;
import Vista.Ventana;

public class Principal {
	public static void main(String[] largs) {
		// Start Swing UI on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			new Ventana();
		});
	}
}