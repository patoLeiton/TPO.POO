package Modelo;

public class ColisionDetector {
    
    public static boolean hayColision(ObjetoJuego obj1, ObjetoJuego obj2) {
        int ancho1 = obj1.getObservador().getAncho();
        int ancho2 = obj2.getObservador().getAncho();
        
        int x1 = obj1.getX();
        int y1 = obj1.getY();
        int x2 = obj2.getX();
        int y2 = obj2.getY();
        
        // Asumimos alto proporcional al ancho para simplificar
        int alto1 = ancho1; 
        int alto2 = ancho2;
        
        return x1 < x2 + ancho2 &&
               x1 + ancho1 > x2 &&
               y1 < y2 + alto2 &&
               y1 + alto1 > y2;
    }
    
    public static boolean hayColisionConEscudo(ObjetoJuego obj, Escudo escudo) {
        int anchoObj = obj.getObservador().getAncho();
        int anchoEscudo = escudo.getObservador().getAncho();
        
        int x1 = obj.getX();
        int y1 = obj.getY();
        int x2 = escudo.getX();
        int y2 = escudo.getY();
        
        int altoObj = anchoObj;
        int altoEscudo = 45; // Alto del escudo
        
        return x1 < x2 + anchoEscudo &&
               x1 + anchoObj > x2 &&
               y1 < y2 + altoEscudo &&
               y1 + altoObj > y2;
    }
}
