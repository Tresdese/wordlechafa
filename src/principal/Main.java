package principal;

import Graficas.Juego;
import db.PalabrasBD;

import javax.swing.SwingUtilities;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtener la palabra a adivinar de la base de datos y convertirla a mayúsculas
        final List<String> palabraAdivinar = PalabrasBD.consultarPalabra();
        if (palabraAdivinar == null) {
            throw new RuntimeException("No se pudo obtener una palabra de la base de datos.");
        }
        final String palabraAdivinarMayusculas = palabraAdivinar.toUpperCase(); // Convertir a mayúsculas

        // Iniciar el juego con la palabra obtenida
        SwingUtilities.invokeLater(() -> new Juego(palabraAdivinarMayusculas));
    }
}