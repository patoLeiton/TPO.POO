package Modelo;

public class Espacio {
    private int ancho;
    private int alto;
    private NaveJugador naveJugador;

    public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador imagenNave) {
        this.ancho= ancho;
        this.alto= alto;
        naveJugador= new NaveJugador(posicionNaveJugadorX,posicionNaveJugadorY, imagenNave,ancho);

    }

    public NaveJugador getNaveJugador(){
        return naveJugador;

    }

}
