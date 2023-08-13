package ejecucion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main_02 extends Application {

    private Pane root;
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private Vertex selectedVertex = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        Button addVertexButton = new Button("Add Vertex");
        addVertexButton.setOnAction(event -> addVertex());

        Button addEdgeButton = new Button("Add Edge");
        addEdgeButton.setOnAction(event -> addEdge());

        root.getChildren().addAll(addVertexButton, addEdgeButton);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addVertex() {
        Vertex vertex = new Vertex(50, 50);
        vertices.add(vertex);
        root.getChildren().add(vertex);
    }

    private void addEdge() {
        if (selectedVertex != null) {
            selectedVertex = null;
            return;
        }

        selectedVertex = vertices.get(0); // You would need a way to select vertices
    }

    private class Vertex extends Circle {
        public Vertex(double x, double y) {
            super(x, y, 20);
            setOnMouseClicked(event -> {
                if (selectedVertex != null) {
                    edges.add(new Edge(selectedVertex.getCenterX(), selectedVertex.getCenterY(), getCenterX(), getCenterY()));
                    root.getChildren().add(edges.get(edges.size() - 1));
                    selectedVertex = null;
                }
            });

            setOnMouseDragged(event -> {
                setCenterX(event.getX());
                setCenterY(event.getY());
            });
        }
    }

    private class Edge extends Line {
        public Edge(double startX, double startY, double endX, double endY) {
            super(startX, startY, endX, endY);
            setOnMouseClicked(event -> root.getChildren().remove(this));
        }
    }
}
