package funciones;

import funciones.Jugador;

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
}
