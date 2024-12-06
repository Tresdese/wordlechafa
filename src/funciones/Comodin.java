package funciones;

import java.util.Random;

import extras.Palabra;
import funciones.Jugador;
import java.util.Random;

public class Comodin {
    private boolean activo;

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

    public String mostrarLetra (String palabraPista) {
        Random random = new Random();

        int index = random.nextInt(palabraPista.length());
        int indexmasuno = index + 1;

        String pista = String.valueOf(palabraPista.charAt(index)) + ", " + indexmasuno;

        return pista;
    }
}
