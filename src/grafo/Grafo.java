package grafo;
import java.util.*;

public class Grafo {
    private Map<Vertice, List<Arista>> listaDeAdyacencia;

    public Grafo() {
        listaDeAdyacencia = new HashMap<>();
    }

    public void agregarVertice(Vertice vertice) {
        if (!listaDeAdyacencia.containsKey(vertice)) {
            listaDeAdyacencia.put(vertice, new ArrayList<>());
        }
    }

    public void agregarArista(Vertice origen, Vertice destino, double peso) {
        listaDeAdyacencia.get(origen).add(new Arista(origen, destino, peso));
        listaDeAdyacencia.get(destino).add(new Arista(destino, origen, peso)); // Para grafos no dirigidos
    }

    public List<Arista> obtenerAristas(Vertice vertice) {
        return listaDeAdyacencia.get(vertice);
    }

    public Set<Vertice> obtenerVertices() {
        return listaDeAdyacencia.keySet();
    }
    public int obtenerComplejidadNotacionO() {
        int sum = 0;

        // Calcula la suma de las longitudes de las listas de adyacencia
        for (List<Arista> aristas : listaDeAdyacencia.values()) {
            sum += aristas.size();
        }

        return sum;
    }

	public void eliminarArista(Arista arista) {
		// TODO Auto-generated method stub
		
	}
}