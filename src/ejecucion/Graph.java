package ejecucion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Note, List<Note>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNote(Note note) {
        if (!adjacencyList.containsKey(note)) {
            adjacencyList.put(note, new ArrayList<>());
        }
    }

    public void addLink(Note source, Note target) {
        addNote(source);
        addNote(target);
        adjacencyList.get(source).add(target);
    }

    public List<Note> getLinkedNotes(Note note) {
        return adjacencyList.getOrDefault(note, new ArrayList<>());
    }

    public List<Note> getNodes() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public int getNodesCount() {
        return adjacencyList.size();
    }

    // Otros métodos relacionados con la gestión del grafo, si es necesario.
}
