// src/funciones/Jugador.java
package funciones;

import java.util.List;

public class Jugador {
    // Atributos
    private int ganadas;
    private int racha;
    private int rachaMaxima;
    private int rachaPerdedora;
    private int totalJugadas;
    private float winRate;

    // Constructor
    public Jugador(int gan, int rach, int rachMax, int rachPerd, int total, float winrate) {
        this.ganadas = gan;
        this.racha = rach;
        this.rachaMaxima = rachMax;
        this.rachaPerdedora = rachPerd;
        this.totalJugadas = total;
        this.winRate = winrate;
    }

    // Getters
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
        actualizarWinRate();
    }

    // Método para registrar una derrota
    public void registrarDerrota() {
        racha = 0;
        rachaPerdedora++;
        totalJugadas++;
        actualizarWinRate();
    }

    // Método para actualizar el win rate
    private void actualizarWinRate() {
        if (totalJugadas > 0) {
            winRate = (float) ganadas / totalJugadas * 100;
        }
    }
}