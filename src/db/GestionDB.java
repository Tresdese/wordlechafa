package db;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class GestionDB {

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
}

