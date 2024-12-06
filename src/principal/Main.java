// src/principal/Main.java
package principal;

import Graficas.Juego;
import extras.Palabra;
import operacionesbd.PalabrasBD;
import funciones.ValidarObjeto;
import javax.swing.SwingUtilities;
import java.util.List;
import funciones.Diccionario;

public class Main {
    public static void main(String[] args) {
        // Obtener la palabra a adivinar de la base de datos y convertirla a mayúsculas
        List<Palabra> palabras = PalabrasBD.consultarPalabra();
        List<Palabra> palabrasDeCincoLetras = ValidarObjeto.validarPalabra(palabras);
        List<Palabra> palabrasMayusculas = ValidarObjeto.listaMayusculas(palabrasDeCincoLetras);
        Diccionario diccioanrio = new Diccionario();

        if (!palabrasMayusculas.isEmpty()) {
            String palabraAdivinar = palabrasMayusculas.get(0).getPalabra();
            SwingUtilities.invokeLater(() -> new Juego(diccioanrio.obtenerPalabraAleatoria(palabrasMayusculas)));
        } else {
            throw new RuntimeException("No se pudo obtener una palabra de cinco letras de la hjbase de datos.");
        }
    }
}