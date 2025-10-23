package Modelo;
import java.util.ArrayList;

public class Espacio {
    private int ancho;
    private int alto;
    private NaveJugador naveJugador;
    private ArrayList <ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();


    public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador imagenNave) {
        this.ancho= ancho;
        this.alto= alto;
        naveJugador= new NaveJugador(posicionNaveJugadorX,posicionNaveJugadorY, imagenNave,ancho);

    }

    public NaveJugador getNaveJugador(){
        return naveJugador;

    }

    public void agregar(ObjetoJuegoActualizable actualizable){
        listaObjetoJuego.add(actualizable);
    }

    public void actualizarPosiciones(){
        for (ObjetoJuegoActualizable actualizable: listaObjetoJuego){
            actualizable.actualizarPosicion();

        }
    }

}
