// src/funciones/ValidarObjeto.java
package funciones;

import extras.EstadoLetra;
import java.util.ArrayList;
import java.util.List;

public class ValidarObjeto {
    public static List<EstadoLetra> validar(String wordToGuess, String guess) {
        List<EstadoLetra> estados = new ArrayList<>();
        char[] wordToGuessArray = wordToGuess.toCharArray();
        char[] guessArray = guess.toCharArray();
        boolean[] guessedCorrectly = new boolean[guess.length()];

        // First pass: mark correct positions
        for (int i = 0; i < guess.length(); i++) {
            if (guessArray[i] == wordToGuessArray[i]) {
                estados.add(EstadoLetra.correcta);
                guessedCorrectly[i] = true;
                wordToGuessArray[i] = '\0'; // Mark as used
            } else {
                estados.add(null);
            }
        }

        // Second pass: mark incorrect positions
        for (int i = 0; i < guess.length(); i++) {
            if (estados.get(i) == null) {
                boolean found = false;
                for (int j = 0; j < wordToGuessArray.length; j++) {
                    if (guessArray[i] == wordToGuessArray[j] && !guessedCorrectly[j]) {
                        estados.set(i, EstadoLetra.posIncorrecta);
                        wordToGuessArray[j] = '\0'; // Mark as used
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    estados.set(i, EstadoLetra.incorrecta);
                }
            }
        }

        return estados;
    }
}