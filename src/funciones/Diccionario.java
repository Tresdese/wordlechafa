package funciones;

import java.util.List;
import java.util.Random;

public class Diccionario {

    private List<String> palabrasDisponibles;
    private Random random;

    // Constructor
    public Diccionario(List<String> palabrasDisponibles) {
        this.palabrasDisponibles = palabrasDisponibles;
        this.random = new Random();
    }

    public String obtenerPalabraAleatoria() {
        int indice = random.nextInt(palabrasDisponibles.size());
        return palabrasDisponibles.get(indice);
    }
}