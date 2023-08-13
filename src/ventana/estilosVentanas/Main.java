package ventana.estilosVentanas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Canvas lienzo;
    private Button botonIniciar;
    private boolean animacionIniciada = false;

    @Override
    public void start(Stage stage) {
        lienzo = new Canvas(WIDTH, HEIGHT);
        botonIniciar = new Button("Iniciar animación");
        botonIniciar.setId("boton");
        botonIniciar.setOnAction(event -> {
            animacionIniciada = true;
        });

        HBox hbox = new HBox(botonIniciar);
        hbox.setSpacing(10);
        BorderPane root = new BorderPane(lienzo);
        root.setBottom(hbox);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Ejemplo Grafo Animado");
        stage.show();

        dibujarGrafo();
        animarGrafo();
    }

    private void dibujarGrafo() {
        GraphicsContext gc = lienzo.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.strokeLine(100, 100, 200, 200);
        gc.strokeLine(200, 200, 300, 100);
        gc.strokeLine(300, 100, 400, 200);
        gc.strokeLine(400, 200, 500, 100);
        gc.strokeLine(500, 100, 600, 200);
    }

    private void animarGrafo() {
        AnimationTimer animationTimer = new AnimationTimer() {
            double x = 0;

            @Override
            public void handle(long now) {
                if (animacionIniciada) {
                    GraphicsContext gc = lienzo.getGraphicsContext2D();
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    gc.setStroke(Color.RED);
                    gc.setLineWidth(3);
                    gc.strokeLine(100 + x, 100, 200 + x, 200);
                    gc.strokeLine(200 + x, 200, 300 + x, 100);
                    gc.strokeLine(300 + x, 100, 400 + x, 200);
                    gc.strokeLine(400 + x, 200, 500 + x, 100);
                    gc.strokeLine(500 + x, 100, 600 + x, 200);
                    x += 5;
                }
            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
