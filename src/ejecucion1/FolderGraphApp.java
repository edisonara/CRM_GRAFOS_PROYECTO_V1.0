package ejecucion1;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.FillRule;
import javafx.stage.Stage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FolderGraphApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Graph graph = new Graph();

        GraphCanvas graphCanvas = new GraphCanvas(graph);
        NodeCreatorCanvas nodeCreatorCanvas = new NodeCreatorCanvas(graph);

        HBox root = new HBox(graphCanvas.getCanvas(), nodeCreatorCanvas.getCanvas());

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Folder Graph");
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphCanvas.draw();
            }
        };
        timer.start();
    }
}

class Graph {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex source, Vertex target) {
        edges.add(new Edge(source, target));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}

class Vertex<T> {
    private T value;
    private double x, y;

    public Vertex(T value, double x, double y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public T getValue() {
        return value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

class Edge {
    private Vertex<?> source;
    private Vertex<?> target;

    public Edge(Vertex<?> source, Vertex<?> target) {
        this.source = source;
        this.target = target;
    }

    public Vertex<?> getSource() {
        return source;
    }

    public Vertex<?> getTarget() {
        return target;
    }
}

class GraphCanvas {
    private Canvas canvas;
    private Graph graph;

    public GraphCanvas(Graph graph) {
        this.graph = graph;
        canvas = new Canvas(800, 600);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Edge edge : graph.getEdges()) {
            Vertex<?> source = edge.getSource();
            Vertex<?> target = edge.getTarget();
            gc.strokeLine(source.getX(), source.getY(), target.getX(), target.getY());
        }

        for (Vertex<?> vertex : graph.getVertices()) {
            double x = vertex.getX();
            double y = vertex.getY();
            gc.setFill(getRandomColor());
            gc.fillOval(x - 20, y - 20, 40, 40);
        }
    }

    private Paint getRandomColor() {
        Random rand = new Random();
        float r = rand.nextDouble();
        float g = rand.nextDouble();
        float b = rand.nextDouble();
        return Color.getHSBColor(r, g, b);
    }

}

class NodeCreatorCanvas {
    private Canvas canvas;
    private Graph graph;

    public NodeCreatorCanvas(Graph graph) {
        this.graph = graph;
        canvas = new Canvas(200, 600);
        canvas.setOnMouseClicked(event -> createNode(event.getX(), event.getY()));
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void createNode(double x, double y) {
        // Simulating folder creation and vertex addition
        Vertex<String> newVertex = new Vertex<>("New Folder", x, y);
        graph.addVertex(newVertex);
    }
}
