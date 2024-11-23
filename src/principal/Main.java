package principal;
import Graficas.Juego;
import javax.swing.SwingUtilities;


//import db.GestionDB;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Conexion a la base de datos


        // interfaz gracias, pero no me salio la interfaz JAJAJA
        String palabraAdivinar = "apple"; // Ejemplo de palabra a adivinar
        SwingUtilities.invokeLater(() -> new Juego(palabraAdivinar));

    }
}

