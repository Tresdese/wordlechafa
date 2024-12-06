package funciones;

import extras.Palabra;
import funciones.ValidarObjeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Diccionario {

    private List<Palabra> palabrasValidadas;
    private Random random;

    // Constructor
    public Diccionario(List<Palabra> palabrasValidadas) {
        this.palabrasValidadas = palabrasValidadas;
    }

    // Metodo para obtener una palabra aleatoria preferiblemente de una lista con palabras de 5 letras
    public String obtenerPalabraAleatoria() {
        String palabraAleatoria = palabrasValidadas.get(random.nextInt(palabrasValidadas.size())).getPalabra();

        return palabraAleatoria;
    }
}