package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Random;

public class GestionDB {

    public static String consultarPalabra() {
        String query = "SELECT palabra FROM palabras WHERE LENGTH(palabra) = 5";
        Connection conexion = ConectarBD("wordlechafa");

        List<String> palabras = new ArrayList<>();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String palabra = rs.getString("palabra");
                palabras.add(palabra);
            }

            if (palabras.isEmpty()) {
                System.out.println("No words found in the database.");
                return null;
            }

            // Return a random word from the list
            return palabras.get(new Random().nextInt(palabras.size()));

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection ConectarBD(String dbName) {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }
        return connection;
    }
}