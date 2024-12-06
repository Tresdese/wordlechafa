// src/funciones/Jugador.java
package funciones;

import java.util.List;

public class Jugador {
    // Atributos
    private int id;
    private String nombreUsuario;
    private int ganadas;
    private int racha;
    private int rachaMaxima;
    private int rachaPerdedora;
    private int totalJugadas;
    private float winRate;

    // Constructores

    // Constructor sin parametros
    public Jugador() {
        this.nombreUsuario = "";
        this.ganadas = 0;
        this.racha = 0;
        this.rachaMaxima = 0;
        this.rachaPerdedora = 0;
        this.totalJugadas = 0;
        this.winRate = 0.0f;
    }

    // Constructor con parametros
    public Jugador(String nombreUsuario, int gan, int rach, int rachMax, int rachPerd, int total, float winrate) {this.nombreUsuario = nombreUsuario;
        this.ganadas = gan;
        this.racha = rach;
        this.rachaMaxima = rachMax;
        this.rachaPerdedora = rachPerd;
        this.totalJugadas = total;
        this.winRate = winrate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getRacha() {
        return racha;
    }

    public int getRachaMaxima() {
        return rachaMaxima;
    }

    public int getRachaPerdedora() {
        return rachaPerdedora;
    }

    public int getTotalJugadas() {
        return totalJugadas;
    }

    public float getWinRate() {
        return winRate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setRacha(int racha) {
        this.racha = racha;
    }

    public void setRachaMaxima(int rachaMaxima) {
        this.rachaMaxima = rachaMaxima;
    }

    public void setRachaPerdedora(int rachaPerdedora) {
        this.rachaPerdedora = rachaPerdedora;
    }

    public void setTotalJugadas(int totalJugadas) {
        this.totalJugadas = totalJugadas;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    // Métodos

    // Metodo para enviar la palabra a adivinarx
    public String enviarPalabra(List<Character> letraList) {
        StringBuilder palabra = new StringBuilder();
        for (Character letra : letraList) {
            palabra.append(letra);
        }
        return palabra.toString();
    }

    // Método para registrar una victoria
    public void registrarVictoria() {
        ganadas++;
        racha++;
        rachaPerdedora = 0;
        if (racha > rachaMaxima) {
            rachaMaxima = racha;
        }
        totalJugadas++;
    }

    // Método para registrar una derrota
    public void registrarDerrota() {
        racha = 0;
        rachaPerdedora += 1;
        totalJugadas++;
    }
}