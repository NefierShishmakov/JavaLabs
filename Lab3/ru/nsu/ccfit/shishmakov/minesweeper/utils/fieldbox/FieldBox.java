package ru.nsu.ccfit.shishmakov.minesweeper.utils.fieldbox;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.slider.TextSlider;

public class FieldBox extends VBox {
    private static final int X_POS = 290;
    private static final int Y_POS = 220;

    private static final int DEFAULT_SPACING = 20;

    public FieldBox(HBox fieldSizes, TextSlider fieldBombsNumSlider)
    {
        this.getChildren().add(fieldSizes);
        this.setSpacing(DEFAULT_SPACING);
        this.getChildren().add(fieldBombsNumSlider);
        this.setLayoutX(X_POS);
        this.setLayoutY(Y_POS);
    }
}
