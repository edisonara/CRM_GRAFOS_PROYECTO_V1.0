package ejecucion;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NoteApp extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Notepad");

        // Create components
        textArea = new TextArea();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveToFile());

        // Layout setup
        VBox vbox = new VBox(textArea, saveButton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane(vbox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Markdown File");
        File file = fileChooser.showSaveDialog(textArea.getScene().getWindow());

        if (file != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
                displayAlert("File saved successfully.", AlertType.INFORMATION);
            } catch (IOException ex) {
                displayAlert("Error while saving the file: " + ex.getMessage(), AlertType.ERROR);
            }
        }
    }

    private void displayAlert(String message, AlertType type) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
        alert.setTitle("Notepad");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
