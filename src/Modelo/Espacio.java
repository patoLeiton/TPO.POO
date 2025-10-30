package Modelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Component;
import javax.swing.SwingUtilities;

public class Espacio {
    private int ancho;
    private int alto;
    private NaveJugador naveJugador;
    private ArrayList<ObjetoJuegoActualizable> listaObjetoJuego = new ArrayList<>();
    private ArrayList<NaveEnemiga> navesEnemigas = new ArrayList<>();
    private ArrayList<Rayo> rayos = new ArrayList<>();
    private ArrayList<Escudo> escudos = new ArrayList<>();
    private int puntuacion = 0;
    private static final boolean DEBUG = false;
    private boolean jugadorAlcanzado = false;

    public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador imagenNave) {
        this.ancho = ancho;
        this.alto = alto;
        naveJugador = new NaveJugador(posicionNaveJugadorX, posicionNaveJugadorY, imagenNave, ancho, alto);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public NaveEnemiga obtenerEnemigoAleatorioActivo() {
        java.util.List<NaveEnemiga> activos = new java.util.ArrayList<>();
        for (NaveEnemiga n : navesEnemigas) {
            if (!n.estaDestruido()) activos.add(n);
        }
        if (activos.isEmpty()) return null;
        int idx = (int) (Math.random() * activos.size());
        return activos.get(idx);
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

    /**
     * Establece el movimiento horizontal para todas las naves enemigas ya existentes.
     */
    public void setMovimientoHorizontalParaTodasNaves(int movimientoHorizontal) {
        for (NaveEnemiga n : navesEnemigas) {
            if (!n.estaDestruido()) {
                n.setMovimientoHorizontal(movimientoHorizontal);
            }
        }
    }
    
    public void agregarEscudo(Escudo escudo) {
        escudos.add(escudo);
    }

    public void actualizarPosiciones() {
        if (DEBUG) System.out.println("[Espacio] tick: lista=" + listaObjetoJuego.size() + " rayos=" + rayos.size() + " naves=" + navesEnemigas.size());
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
        
        detectarColisiones();
        limpiarDestruidos();
    }
    
    private void detectarColisiones() {
        // Colisiones entre rayos y objetos (evitar "friendly fire")
        for (Rayo rayo : rayos) {
            if (rayo.estaDestruido()) continue;

            if (rayo.isHaciaArriba()) {
                // proyectiles del jugador afectan a enemigos y escudos
                for (NaveEnemiga enemiga : navesEnemigas) {
                    if (enemiga.estaDestruido()) continue;
                    if (ColisionDetector.hayColision(rayo, enemiga)) {
                        rayo.destruir();
                        enemiga.destruir();
                        puntuacion += 10;
                        System.out.println("¡Enemigo destruido! Puntuación: " + puntuacion);
                        // Si con esta destrucción no quedan enemigos, otorgar bonus por limpiar la oleada
                        if (hayVictoria()) {
                            puntuacion += 200;
                            System.out.println("¡BONUS: todos los enemigos destruidos! +200 puntos. Puntuación: " + puntuacion);
                        }
                        break;
                    }
                }

                if (!rayo.estaDestruido()) {
                    for (Escudo escudo : escudos) {
                        if (escudo.estaDestruido()) continue;
                        if (ColisionDetector.hayColisionConEscudo(rayo, escudo)) {
                            rayo.destruir();
                            // El rayo proviene del jugador -> daño por impacto de jugador
                            escudo.recibirDanioPorImpactoJugador();
                            break;
                        }
                    }
                }
            } else {
                // proyectiles enemigos afectan al jugador y a los escudos
                if (naveJugador != null) {
                    if (ColisionDetector.hayColision(rayo, naveJugador)) {
                        rayo.destruir();
                        jugadorAlcanzado = true;
                        System.out.println("¡Jugador alcanzado!");
                        continue;
                    }
                }

                for (Escudo escudo : escudos) {
                    if (escudo.estaDestruido()) continue;
                    if (ColisionDetector.hayColisionConEscudo(rayo, escudo)) {
                        rayo.destruir();
                        // Rayo enemigo impacta el escudo
                        escudo.recibirDanioPorImpactoEnemigo();
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
                // intentar remover la representación visual (si existe) del contenedor
                try {
                    Observador obs = obj.getObservador();
                    if (obs instanceof Component) {
                        Component comp = (Component) obs;
                        if (comp.getParent() != null) {
                            // remoción segura en el hilo de despacho de eventos
                            SwingUtilities.invokeLater(() -> comp.getParent().remove(comp));
                        }
                    }
                } catch (Exception e) {
                    // ignorar: eliminación visual no crítica
                }
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
        if (jugadorAlcanzado) return true;
        for (NaveEnemiga enemiga : navesEnemigas) {
            if (!enemiga.estaDestruido() && enemiga.getY() > alto - 150) {
                return true;
            }
        }
        return false;
    }
}