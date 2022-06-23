package ru.nsu.ccfit.shishmakov.minesweeper.utils.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameResultsAlertBox {
    private static final String ALERT_BOX_NAME = "Game results";
    private static final String CLOSE_BUTTON_TEXT = "Go back to the field";

    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;
    private static final int DEFAULT_SPACING = 20;

    public static void display(String gameResultsMessage)
    {
        Stage stage = new Stage();
        stage.setTitle(ALERT_BOX_NAME);
        stage.setResizable(false);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);

        Label gameResultsLabel = new Label(gameResultsMessage);

        Button closeButton = new Button(CLOSE_BUTTON_TEXT);

        closeButton.setOnAction(actionEvent -> stage.close());

        VBox layout = new VBox(DEFAULT_SPACING);

        layout.getChildren().addAll(gameResultsLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
