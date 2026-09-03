package nori.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Displays one user or Nori message in the conversation.
 */
public class DialogBox extends HBox {
    private static final double MESSAGE_MAX_WIDTH = 340;

    private DialogBox(String text, String speaker, boolean isUser) {
        Label avatar = new Label(speaker);
        Label message = new Label(text);
        Region spacer = new Region();

        avatar.getStyleClass().add("avatar");
        message.getStyleClass().add("dialog-message");
        message.setMaxWidth(MESSAGE_MAX_WIDTH);
        message.setWrapText(true);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getStyleClass().add("dialog-box");
        if (isUser) {
            setAlignment(Pos.TOP_RIGHT);
            message.getStyleClass().add("user-message");
            getChildren().addAll(spacer, message, avatar);
        } else {
            setAlignment(Pos.TOP_LEFT);
            avatar.getStyleClass().add("nori-avatar");
            message.getStyleClass().add("nori-message");
            getChildren().addAll(avatar, message, spacer);
        }
    }

    /** Returns a dialog box containing a user message. */
    public static DialogBox createUserDialog(String text) {
        return new DialogBox(text, "YOU", true);
    }

    /** Returns a dialog box containing a Nori response. */
    public static DialogBox createNoriDialog(String text) {
        return new DialogBox(text, "N", false);
    }
}
