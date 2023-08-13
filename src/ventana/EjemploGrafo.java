package ventana;
import grafo.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EjemploGrafo extends Application {

    private Grafo grafo = new Grafo();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Vertice v3 = new Vertice("C");

        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        grafo.agregarVertice(v3);

        grafo.agregarArista(v1, v2, 5.0);
        grafo.agregarArista(v2, v3, 3.0);

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);

        // Dibuja los vértices
        double centerX = 400;
        double centerY = 300;
        double radius = 150;
        double angleStep = 2 * Math.PI / grafo.obtenerVertices().size();
        double angle = 0;
        int index = 0;

        for (Vertice vertice : grafo.obtenerVertices()) {
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle circle = new Circle(x, y, 20, Color.DARKBLUE);
            root.getChildren().add(circle);

            Text text = new Text((String) vertice.obtenerDato());
            text.setX(x - 10);
            text.setY(y - 25);
            root.getChildren().add(text);

            angle += angleStep;
            index++;
        }

        // Dibuja las aristas
        for (Vertice vertice : grafo.obtenerVertices()) {
            double x1 = centerX + radius * Math.cos(angle - angleStep);
            double y1 = centerY + radius * Math.sin(angle - angleStep);

            for (Arista arista : grafo.obtenerAristas(vertice)) {
                Vertice destino = arista.obtenerDestino();
                double x2 = centerX + radius * Math.cos(angleStep * index);
                double y2 = centerY + radius * Math.sin(angleStep * index);

                Line line = new Line(x1, y1, x2, y2);
                root.getChildren().add(line);
                index++;
            }

            angle -= angleStep;
        }

        primaryStage.setTitle("Grafo Animado");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
