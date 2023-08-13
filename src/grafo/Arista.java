package grafo;

public class Arista {
    private Vertice origen;
    private Vertice destino;
    private double peso;

    public Arista(Vertice origen, Vertice destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice obtenerOrigen() {
        return origen;
    }

    public Vertice obtenerDestino() {
        return destino;
    }

    public double obtenerPeso() {
        return peso;
    }
}
