package Modelo;
import java.util.ArrayList;
import java.util.Iterator;

public class Espacio {
    private int ancho;
    private int alto;
    private NaveJugador naveJugador;
    private ArrayList<ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();
    private ArrayList<NaveEnemiga> navesEnemigas = new ArrayList<>();
    private ArrayList<Rayo> rayos = new ArrayList<>();
    private ArrayList<Escudo> escudos = new ArrayList<>();
    private int puntuacion = 0;

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
        if (actualizable instanceof Rayo) {
            rayos.add((Rayo) actualizable);
        }
    }

    public void agregarNaveEnemiga(NaveEnemiga nave) {
        navesEnemigas.add(nave);
        listaObjetoJuego.add(nave);
    }
    
    public void agregarEscudo(Escudo escudo) {
        escudos.add(escudo);
    }

    public void actualizarPosiciones() {
        // Verificar si algún enemigo tocó el borde
        boolean cambiarDireccion = false;
        for (NaveEnemiga enemiga : navesEnemigas) {
            if (!enemiga.estaDestruido()) {
                if (enemiga.isMoviendoDerecha() && enemiga.getX() >= ancho - 60) {
                    cambiarDireccion = true;
                    break;
                } else if (!enemiga.isMoviendoDerecha() && enemiga.getX() <= 10) {
                    cambiarDireccion = true;
                    break;
                }
            }
        }

        if (cambiarDireccion) {
            for (NaveEnemiga enemiga : navesEnemigas) {
                if (!enemiga.estaDestruido()) {
                    enemiga.cambiarDireccion();
                }
            }
        }

        // Actualizar todas las posiciones
        for (ObjetoJuegoActualizable actualizable : listaObjetoJuego) {
            actualizable.actualizarPosicion();
        }
        
        // Detectar colisiones
        detectarColisiones();
        
        // Limpiar objetos destruidos
        limpiarDestruidos();
    }
    
    private void detectarColisiones() {
        // Colisiones entre rayos y enemigos
        for (Rayo rayo : rayos) {
            if (rayo.estaDestruido()) continue;
            
            for (NaveEnemiga enemiga : navesEnemigas) {
                if (enemiga.estaDestruido()) continue;
                
                if (ColisionDetector.hayColision(rayo, enemiga)) {
                    rayo.destruir();
                    enemiga.destruir();
                    puntuacion += 10;
                    System.out.println("¡Enemigo destruido! Puntuación: " + puntuacion);
                    break;
                }
            }
            
            // Colisiones entre rayos y escudos
            if (!rayo.estaDestruido()) {
                for (Escudo escudo : escudos) {
                    if (escudo.estaDestruido()) continue;
                    
                    if (ColisionDetector.hayColisionConEscudo(rayo, escudo)) {
                        rayo.destruir();
                        escudo.recibirDanio();
                        break;
                    }
                }
            }
        }
    }
    
    private void limpiarDestruidos() {
        Iterator<ObjetoJuegoActualizable> it = listaObjetoJuego.iterator();
        while (it.hasNext()) {
            ObjetoJuegoActualizable obj = it.next();
            if (obj instanceof Destruible && ((Destruible) obj).estaDestruido()) {
                it.remove();
            }
        }
        
        rayos.removeIf(Destruible::estaDestruido);
        navesEnemigas.removeIf(Destruible::estaDestruido);
        escudos.removeIf(Destruible::estaDestruido);
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public int getEnemigosRestantes() {
        int count = 0;
        for (NaveEnemiga enemiga : navesEnemigas) {
            if (!enemiga.estaDestruido()) {
                count++;
            }
        }
        return count;
    }
    
    public boolean hayVictoria() {
        return getEnemigosRestantes() == 0;
    }
    
    public boolean hayGameOver() {
        // Game over si algún enemigo llega muy abajo
        for (NaveEnemiga enemiga : navesEnemigas) {
            if (!enemiga.estaDestruido() && enemiga.getY() > alto - 150) {
                return true;
            }
        }
        return false;
    }
}