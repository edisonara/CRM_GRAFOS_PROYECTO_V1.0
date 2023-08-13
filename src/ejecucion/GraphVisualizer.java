package ejecucion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GraphVisualizer extends Application {

    private Graph graph;
  
    private Map<Note, Double[]> nodePositions;

    @Override
    public void start(Stage primaryStage) {
        graph = new Graph();
        // Agregar notas y enlaces para demostración
        Note note1 = new Note("Note 1", "Contenido de la nota 1");
        Note note2 = new Note("Note 2", "Contenido de la nota 2");
        Note note3 = new Note("Note 3", "Contenido de la nota 3");
        graph.addLink(note1, note2);
        graph.addLink(note1, note3);

        primaryStage.setTitle("Graph Visualizer");

        Canvas canvas = new Canvas(800, 600);
        drawGraph(canvas.getGraphicsContext2D());

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGraph(GraphicsContext gc) {
        // Lógica para dibujar el grafo en el canvas
        // Puedes usar métodos de la clase GraphicsContext para dibujar los nodos y arcos.
        // Por ejemplo: gc.fillOval(x, y, diameter, diameter) para dibujar un nodo.
    	nodePositions = new HashMap<>();
        int numNodes = graph.getNodesCount();
        double centerX = gc.getCanvas().getWidth() / 2.0;
        double centerY = gc.getCanvas().getHeight() / 2.0;
        double radius = 200.0;
        double angleIncrement = 2 * Math.PI / numNodes;
        int i = 0;

        for (Note node : graph.getNodes()) {
            double x = centerX + radius * Math.cos(i * angleIncrement);
            double y = centerY + radius * Math.sin(i * angleIncrement);
            nodePositions.put(node, new Double[]{x, y});
            gc.setFill(Color.BLUE);
            gc.fillOval(x - 20, y - 20, 40, 40); // Dibuja un nodo como un círculo
            gc.setFill(Color.BLACK);
            gc.fillText(node.getTitle(), x, y - 30); // Dibuja el título del nodo
            i++;
        }

        gc.setStroke(Color.BLACK);
        for (Note source : graph.getNodes()) {
            Double[] sourcePosition = nodePositions.get(source);
            for (Note target : graph.getLinkedNotes(source)) {
                Double[] targetPosition = nodePositions.get(target);
                gc.strokeLine(sourcePosition[0], sourcePosition[1], targetPosition[0], targetPosition[1]); // Dibuja el arco entre nodos conectados
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
