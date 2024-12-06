package extras;

// import java.util.List;

public class Palabra {
    private int id;
    private String palabra;
    private int longitud;
    private String descripcion;

    // Constructor vacio
    public Palabra() {}

    // Constructor con parametros
    public Palabra(int id, String palabra, int longitud, String descripcion) {
        this.id = id;
        this.palabra = palabra;
        this.longitud = longitud;
        this.descripcion = descripcion;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getLongitud() {
        return longitud;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // @Override
    // public String toString() {
    //     return "Palabra{" +
    //             "palabra='" + palabra + '\'' +
    //             ", longitud=" + longitud +
    //             ", descripcion='" + descripcion + '\'' +
    //             '}';
    // }
}