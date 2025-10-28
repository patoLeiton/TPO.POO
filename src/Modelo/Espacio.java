package Modelo;
import java.util.ArrayList;

public class Espacio {
    private int ancho;
    private int alto;
    private NaveJugador naveJugador;
    private ArrayList<ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();
    private ArrayList<NaveEnemiga> navesEnemigas = new ArrayList<>();

    public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador imagenNave) {
        this.ancho = ancho;
        this.alto = alto;
        naveJugador = new NaveJugador(posicionNaveJugadorX, posicionNaveJugadorY, imagenNave, ancho);
    }

    public NaveJugador getNaveJugador() {
        return naveJugador;
    }

    public void agregar(ObjetoJuegoActualizable actualizable) {
        listaObjetoJuego.add(actualizable);
    }

    public void agregarNaveEnemiga(NaveEnemiga nave) {
        navesEnemigas.add(nave);
        listaObjetoJuego.add(nave);
    }

    public void actualizarPosiciones() {
        // Verificar si algún enemigo tocó el borde
        boolean cambiarDireccion = false;
        for (NaveEnemiga enemiga : navesEnemigas) {
            if (enemiga.isMoviendoDerecha() && enemiga.getX() >= ancho - 60) {
                cambiarDireccion = true;
                break;
            } else if (!enemiga.isMoviendoDerecha() && enemiga.getX() <= 10) {
                cambiarDireccion = true;
                break;
            }
        }

        if (cambiarDireccion) {
            for (NaveEnemiga enemiga : navesEnemigas) {
                enemiga.cambiarDireccion();
            }
        }

        // Actualizar todas las posiciones
        for (ObjetoJuegoActualizable actualizable : listaObjetoJuego) {
            actualizable.actualizarPosicion();
        }
    }
}