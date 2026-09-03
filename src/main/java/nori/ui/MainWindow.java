package nori.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import nori.Nori;

/**
 * Controls Nori's main conversation window.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nori nori;

    /** Configures behavior that depends only on loaded FXML controls. */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) ->
                scrollPane.setVvalue(1.0));
    }

    /**
     * Connects the window to Nori's command-processing logic.
     *
     * @param nori Nori instance that handles user commands.
     */
    public void setNori(Nori nori) {
        this.nori = nori;
        dialogContainer.getChildren().add(
                DialogBox.createNoriDialog("Hello! I'm Nori. What can I do for you?"));
        Platform.runLater(userInput::requestFocus);
    }

    /** Handles a command submitted through the text field or Send button. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = nori.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.createUserDialog(input),
                DialogBox.createNoriDialog(response));
        userInput.clear();

        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}
