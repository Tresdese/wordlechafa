package operacionesbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConexionBD;

public class PalabrasBD {

    // Create
    public static void insertarPalabra(String palabra, int lon, String desc) {
        String query = "INSERT INTO palabras (palabra, longitud, descripcion) VALUES ('" + palabra + "', " + lon + ", '" + desc + "')";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Palabra insertada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    // Read
    public static List<String> consultarPalabra() {
        String query = "SELECT * FROM palabras";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        List<String> palabras = new ArrayList<String>();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String palabra = rs.getString("palabra");
                palabras.add(palabra);
                //    System.out.println(rs.getString("palabra"));
            }

            return palabras;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update
    public static void actualizarPalabraLongitud(String palabra, int lon) {
        String query = "UPDATE palabras SET longitud = " + lon + " WHERE palabra = '" + palabra + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Palabra actualizada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void actualizarPalabra(String palabra) {
        String query = "UPDATE palabras SET palabra = '" + palabra + "' WHERE palabra = '" + palabra + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Palabra actualizada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void actualizarDescripcion(String palabra, String desc) {
        String query = "UPDATE palabras SET descripcion = '" + desc + "' WHERE palabra = '" + palabra + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Descripción actualizada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Delete
    public static void eliminarPalabra(String palabra) {
        String query = "DELETE FROM palabras where palabra = '" + palabra + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Palabra eliminada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
