package funciones;

import extras.Palabra;
import funciones.ValidarObjeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Diccionario {

    private List<Palabra> palabrasValidadas;

    // Constructor
    public Diccionario() {}

    // Metodo para obtener una palabra aleatoria preferiblemente de una lista con palabras de 5 letras
    public String obtenerPalabraAleatoria(List <Palabra> palabrasValidadas) {
        Random random = new Random();
        String palabraAleatoria = palabrasValidadas.get(random.nextInt(palabrasValidadas.size())).getPalabra();

        return palabraAleatoria;
    }
}