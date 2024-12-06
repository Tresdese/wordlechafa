package operacionesbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConexionBD;


public class JugadorBD {

    // Create
    public static void insertarJugador(String usuario, int totalJugadas, int totalGanadas, float winrate, int rachaMaxima) {
        String query = "INSERT INTO jugador (usuario) VALUES ('" + usuario + "', " + totalJugadas + ", " + totalGanadas + ", " + winrate + ", " + rachaMaxima + ")";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");
    
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Jugador insertado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Read
    public static List<String> consultarJugador() {
        String query = "SELECT * FROM jugador";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        List<String> usuarios = new ArrayList<String>();

        try {

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String usuario = rs.getString("usuario");
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Update

    // Actualizar nombre de usuario
    public static void actualizarJugadorUsuario(String usuario, String nuevoUsuario) {
        String query = "UPDATE jugador SET usuario = '" + nuevoUsuario + "' WHERE usuario = '" + usuario + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Usuario actualizado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Actualizar total de jugadas
    public static void actualizarJugadorTotalJugadas(String usuario, int totalJugadas) {
        String query = "UPDATE jugador SET totalJugadas = " + totalJugadas + " WHERE usuario = '" + usuario + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Total de jugadas actualizado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Actualizar total de ganadas
    public static void actualizarJugadorTotalGanadas(String usuario, int totalGanadas) {
        String query = "UPDATE jugador SET totalGanadas = " + totalGanadas + " WHERE usuario = '" + usuario + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Total de ganadas actualizado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // el winrate no se actualiza ya que este se calcula en base a las ganadas y perdidas

    // Actualizar racha maxima
    public static void actualizarJugadorRachaMaxima(String usuario, int rachaMaxima) {
        String query = "UPDATE jugador SET rachaMaxima = " + rachaMaxima + " WHERE usuario = '" + usuario + "'";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Racha máxima actualizada correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
