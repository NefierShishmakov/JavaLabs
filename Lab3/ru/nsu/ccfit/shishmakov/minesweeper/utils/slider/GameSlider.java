package ru.nsu.ccfit.shishmakov.minesweeper.utils.slider;

import javafx.scene.control.Slider;

public class GameSlider extends Slider {
    private final static int SLIDER_WIDTH = 500;
    private final static String SLIDER_STYLE = "-fx-font-size: 30; -fx-font-weight: bold";

    public GameSlider(int minValue, int maxValue)
    {
        this.setStyle(SLIDER_STYLE);
        this.setMin(minValue);
        this.setMax(maxValue);
        this.setPrefWidth(SLIDER_WIDTH);
        this.setShowTickMarks(true);
        this.setShowTickLabels(true);
        this.setSnapToTicks(true);
        this.setMinorTickCount(0);
        this.setMajorTickUnit(1);
    }
}
