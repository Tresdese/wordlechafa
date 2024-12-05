package db;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class GestionDB {
   // Conexión a la base de datos
   public static Connection ConectarBD(String bd) {
       Connection conexion;
       String host = "jdbc:mysql://localhost/";
       String user = "root";
       String pass = "admin";

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

       String query = "SELECT * FROM palabras";

       try {
           Statement stmt = conexion.createStatement();
           ResultSet rs = stmt.executeQuery(query);
           while (rs.next()) {
               String palabra = rs.getString("palabra");
            //    System.out.println(rs.getString("palabra"));
               return palabra;
           }
           
        } catch (SQLException e) {
           System.out.println(e.getMessage());
           throw new RuntimeException(e);
        }
        
        return null; // Return null if no words are found
   }
}
