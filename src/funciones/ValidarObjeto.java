package funciones;

import extras.EstadoLetra;
import java.util.List;

public class ValidarObjeto {
    private char letra;
    private String palabraValida;
    private EstadoLetra estado;

    // Constructor
    public ValidarObjeto(char letra, String palabraValida, EstadoLetra estado) {
        this.letra = letra;
        this.palabraValida = palabraValida;
        this.estado = estado;
    }

    // Getters
    public char getLetra() {
        return letra;
    }

    public String getPalabraValida() {
        return palabraValida;
    }

    public EstadoLetra getEstado() {
        return estado;
    }

    // Setters
    public void setLetra(char letra) {
        this.letra = letra;
    }

    public void setPalabrasValidadas(String palabraValida) {
        this.palabraValida = palabraValida;
    }

    public void setEstado(EstadoLetra estado) {
        this.estado = estado;
    }

    public char validarLetra(char letra, String palabraValida) {
        
    }
}
