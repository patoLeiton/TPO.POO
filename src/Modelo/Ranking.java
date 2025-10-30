package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase simple para administrar el ranking de jugadores.
 * Formato de persistencia: cada línea "nombre;puntaje"
 */
public class Ranking {
    private static final String FILE_NAME = "scores.txt";
    private Map<String, Integer> scores;

    public Ranking() {
        scores = new HashMap<>();
        load();
    }

    private void load() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    String name = parts[0];
                    try {
                        int score = Integer.parseInt(parts[1]);
                        scores.put(name, score);
                    } catch (NumberFormatException ex) {
                        // ignorar línea mal formada
                    }
                }
            }
        } catch (Exception e) {
            // ignoramos errores de lectura; el ranking quedará vacío
        }
    }

    private void save() {
        File f = new File(FILE_NAME);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (Map.Entry<String, Integer> e : scores.entrySet()) {
                bw.write(e.getKey() + ";" + e.getValue());
                bw.newLine();
            }
        } catch (Exception e) {
            // si falla guardar, no hacemos nada
        }
    }

    /**
     * Agrega o actualiza el puntaje de un jugador.
     * Si el jugador existe y el nuevo puntaje es mayor, lo reemplaza.
     * Si no existe, lo agrega.
     */
    public synchronized void addOrUpdate(String name, int score) {
        if (name == null) return;
        name = name.trim();
        if (name.isEmpty()) return;
        Integer prev = scores.get(name);
        if (prev == null || score > prev) {
            scores.put(name, score);
            save();
        }
    }

    /**
     * Devuelve la lista de entradas ordenadas de mayor a menor puntaje.
     */
    public synchronized List<Map.Entry<String, Integer>> getSortedDesc() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int c = Integer.compare(o2.getValue(), o1.getValue()); // descendente
                if (c == 0) return o1.getKey().compareToIgnoreCase(o2.getKey());
                return c;
            }
        });
        return list;
    }
}
