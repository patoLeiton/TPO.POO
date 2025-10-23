package Vista;

import java.awt.Color;

import javax.swing.JLabel;

public class ImagenRayo extends JLabel{
    private int ancho;
    private int alto;

    public ImagenRayo(){
        this.ancho=4;
        this.alto= 20;
        setOpaque(true);
        setBackground(Color.PINK);

    }

    public void mover(int x, int y){

        setBounds(x,y,ancho,alto);
    }

}
