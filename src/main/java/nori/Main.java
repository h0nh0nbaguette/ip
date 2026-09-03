package nori;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Displays Nori's JavaFX user interface.
 */
public class Main extends Application {
    /**
     * Creates and displays Nori's primary window.
     *
     * @param stage primary stage supplied by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        Label greeting = new Label("Hello! I'm Nori.");
        Scene scene = new Scene(greeting, 400, 600);

        stage.setTitle("Nori");
        stage.setScene(scene);
        stage.show();
    }
}
