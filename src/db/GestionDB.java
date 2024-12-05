package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionDB {
    // Conexión a la base de datos
    public static Connection ConectarBD(String bd) {
        Connection conexion;
        String host = "jdbc:mysql://localhost/";
        String user = "root";
        String pass = "123456";

        System.out.println("Conectando a la base de datos...");

        try {
            conexion = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static String consultarPalabra() {
        Connection conexion = ConectarBD("wordlechafa");

        String query = "SELECT palabra FROM palabras WHERE LENGTH(palabra) = 5";
        List<String> palabras = new ArrayList<>();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                palabras.add(rs.getString("palabra"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        if (palabras.isEmpty()) {
            return null; // Return null if no words are found
        }

        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));
    }
}