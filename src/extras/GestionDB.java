//package extras;
//
//import db.ConexionBD;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class GestionDB {
//
//    public static String consultarPalabra() {
//        String query = "SELECT * FROM palabras";
//
//        ConexionBD connect = new ConexionBD();
//        Connection conexion = connect.ConectarBD("wordlechafa");
//
//        try {
//            Statement stmt = conexion.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String palabra = rs.getString("palabra");
//                //    System.out.println(rs.getString("palabra"));
//                return palabra;
//            }
//
//            } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//            }
//
//            return null; // Return null if no words are found
//    }
//}
