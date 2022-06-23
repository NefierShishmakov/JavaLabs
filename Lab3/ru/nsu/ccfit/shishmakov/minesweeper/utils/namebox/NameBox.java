package ru.nsu.ccfit.shishmakov.minesweeper.utils.namebox;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NameBox extends HBox {
    private final static String NAME_STYLE = "-fx-font-size: 30; -fx-text-fill: #E30613;";
    private final static String TEXT_FIELD_STYLE = "-fx-background-image: url('text_field_background.png'); " +
            "-fx-font-size: 20; -fx-text-fill: #4DEEFF;";

    private final static String DEFAULT_NAME_BOX_LABEL = "Enter your nickname:";

    private final static int X_POSITION = 410;
    private final static int Y_POSITION = 110;

    private final static int DEFAULT_SPACING = 10;

    public NameBox()
    {
        Label nameLabel = new Label(DEFAULT_NAME_BOX_LABEL);
        nameLabel.setStyle(NAME_STYLE);

        this.nameTextField = new TextField();
        this.nameTextField.setStyle(TEXT_FIELD_STYLE);

        this.setSpacing(DEFAULT_SPACING);

        this.setLayoutX(X_POSITION);
        this.setLayoutY(Y_POSITION);

        this.getChildren().addAll(nameLabel, this.nameTextField);
    }

    public TextField getNameTextField()
    {
        return nameTextField;
    }

    private final TextField nameTextField;
}
