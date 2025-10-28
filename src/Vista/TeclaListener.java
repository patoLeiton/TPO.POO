package Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import Controlador.JuegoController;

public class TeclaListener extends KeyAdapter {
    private JuegoController controlador;

    public TeclaListener(JuegoController controlador){
        this.controlador = controlador;
    }

    @Override
    public void keyPressed(KeyEvent evento) {
        int tecla = evento.getKeyCode();
        
        if (tecla == KeyEvent.VK_LEFT) {
            controlador.moverNaveJugadorIzquierda();
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            controlador.moverNaveJugadorDerecha();
        }
}
}
