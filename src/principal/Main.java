package principal;

import Graficas.Juego;
import db.GestionDB;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Obtener la palabra a adivinar de la base de datos y convertirla a mayúsculas
        final String palabraAdivinar = GestionDB.consultarPalabra();
        if (palabraAdivinar == null) {
            throw new RuntimeException("No se pudo obtener una palabra de la base de datos.");
        }
        final String palabraAdivinarMayusculas = palabraAdivinar.toUpperCase(); // Convertir a mayúsculas

        // Iniciar el juego con la palabra obtenida
        SwingUtilities.invokeLater(() -> new Juego(palabraAdivinarMayusculas));
    }
}