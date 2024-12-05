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
    public Jugador (int gan, int rach, int rachMax, int rachPerd, int total, float winrate) {
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

    // Setters
    public float getWinRate() {
        return winRate;
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
    public String enviarPalabra(List<Character> letraList) {
        
        String palabra = "";

        for (Character letra : letraList) {
            palabra += letra;
        }

        return palabra;
    }
}
