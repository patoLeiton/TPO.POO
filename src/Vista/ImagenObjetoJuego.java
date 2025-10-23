package Vista;

import javax.swing.JLabel;

import Modelo.Observador;

public abstract class ImagenObjetoJuego extends JLabel implements Observador {
private int ancho;
private int alto;
private int y;
private int PosicionMediaX;


public ImagenObjetoJuego(int ancho,int alto ){
    this.ancho= ancho;
    this.alto= alto;
   
}

public int getAncho(){
    return ancho;
}

public int getY(){
    return y;
}

public int getAlto(){
    return alto;
}

public int getPosicionMediaX(){
    return PosicionMediaX;
}

public void mover(int x, int y){
     setBounds(x, y, ancho, alto);
}
}