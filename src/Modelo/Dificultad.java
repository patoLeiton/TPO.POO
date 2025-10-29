package Modelo;

/**
 * Dificultad del juego con parámetros para velocidad de movimiento enemigo
 * y parámetros de disparo.
 */
public enum Dificultad {
    CADETE(1, 0.03, 1),
    GUERRERO(2, 0.06, 2),
    MASTER(3, 0.10, 3);

    private final int movimientoHorizontal;
    private final double probDisparoPorIntento;
    private final int maxDisparosPorTick;

    Dificultad(int movimientoHorizontal, double probDisparoPorIntento, int maxDisparosPorTick) {
        this.movimientoHorizontal = movimientoHorizontal;
        this.probDisparoPorIntento = probDisparoPorIntento;
        this.maxDisparosPorTick = maxDisparosPorTick;
    }

    public int getMovimientoHorizontal() {
        return movimientoHorizontal;
    }

    public double getProbDisparoPorIntento() {
        return probDisparoPorIntento;
    }

    public int getMaxDisparosPorTick() {
        return maxDisparosPorTick;
    }
}
