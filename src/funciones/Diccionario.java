package funciones;

import java.util.List;

public class Diccionario {
    
   private List<String> palabrasDisponibles;

   int tamanoPalabra = palabrasDisponibles.size();

   // Constructor
    public Diccionario(List<String> palabrasDisponibles) {
         this.palabrasDisponibles = palabrasDisponibles;
    }

   public String obtenerPalabraAleatoria() {
        
        return palabrasDisponibles.get(indice);
   }
}
