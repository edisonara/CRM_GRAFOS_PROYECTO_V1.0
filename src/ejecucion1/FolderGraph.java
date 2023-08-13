package ejecucion1;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class FolderGraph {

    private Canvas canvas;
    private List<FolderNode> nodes;
    private List<Edge> edges;

    public FolderGraph() {
        canvas = new Canvas(800, 500);
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void addNode(String folderName) {
        FolderNode newNode = new FolderNode(folderName);
        nodes.add(newNode);
        redrawGraph();
    }

    public void addEdge(int sourceIndex, int targetIndex) {
        if (sourceIndex < 0 || sourceIndex >= nodes.size() ||
            targetIndex < 0 || targetIndex >= nodes.size()) {
            return; // Index out of bounds
        }

        FolderNode source = nodes.get(sourceIndex);
        FolderNode target = nodes.get(targetIndex);
        Edge newEdge = new Edge(source, target);
        edges.add(newEdge);
        redrawGraph();
    }

    private void redrawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Edge edge : edges) {
            edge.draw(gc);
        }

        for (FolderNode node : nodes) {
            node.draw(gc);
        }
    }

    private class FolderNode {
        private String folderName;
        private double x, y;
        private static final double WIDTH = 60;
        private static final double HEIGHT = 40;

        FolderNode(String folderName) {
            this.folderName = folderName;
            this.x = Math.random() * (canvas.getWidth() - WIDTH);
            this.y = Math.random() * (canvas.getHeight() - HEIGHT);
        }

        void draw(GraphicsContext gc) {
            gc.setFill(Color.GREEN);
            gc.fillRect(x, y, WIDTH, HEIGHT);
            gc.setFill(Color.BLACK);
            gc.fillText(folderName, x + 10, y + 20);
        }
    }

    private class Edge {
        private FolderNode source, target;

        Edge(FolderNode source, FolderNode target) {
            this.source = source;
            this.target = target;
        }

        void draw(GraphicsContext gc) {
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.strokeLine(source.x + FolderNode.WIDTH / 2, source.y + FolderNode.HEIGHT,
                          target.x + FolderNode.WIDTH / 2, target.y);
        }
    }
}
