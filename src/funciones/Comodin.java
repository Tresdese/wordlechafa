package funciones;

import java.util.Random;

import extras.Palabra;
import funciones.Jugador;
import java.util.Random;

public class Comodin {
    private boolean activo;
    private Random random;

    // Constructor
    public Comodin() {}

    // Getters
    public boolean getActivo() {
        return activo;
    }

    // Setters
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Métodos
    public void activar(int rachaPerdedora) {
        if (rachaPerdedora >= 3) {
            this.activo = true;
        } else {
            this.activo = false;
        }
    }

    public char mostrarLetra (String palabraPista) {
        int index = random.nextInt(palabraPista.length());

        return palabraPista.charAt(index);
    }
}
