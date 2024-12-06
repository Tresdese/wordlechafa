package operacionesbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConexionBD;
import funciones.Jugador;


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
    public static List<Jugador> consultarJugador() {
        String query = "SELECT * FROM jugador";
        
        ConexionBD connect = new ConexionBD();
        Connection conexion = connect.ConectarBD("wordlechafa");

        List<Jugador> usuario = new ArrayList<Jugador>();

        try {

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String usuarioNom = rs.getString("usuario");
                int totalJugadas = rs.getInt("totalJugadas");
                int totalGanadas = rs.getInt("totalGanadas");
                float winrate = rs.getFloat("winrate");
                int rachaMaxima = rs.getInt("maximaRacha");

                Jugador j = new Jugador();

                j.setId(id);
                j.setNombreUsuario(usuarioNom);
                j.setTotalJugadas(totalJugadas);
                j.setGanadas(totalGanadas);
                j.setWinRate(winrate);
                j.setRachaMaxima(rachaMaxima);

                usuario.add(j);
            }

            return usuario;

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
