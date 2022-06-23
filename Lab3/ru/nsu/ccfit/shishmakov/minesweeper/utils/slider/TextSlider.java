package ru.nsu.ccfit.shishmakov.minesweeper.utils.slider;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TextSlider extends VBox {

    private final static String TEXT_STYLE = "-fx-font-size: 20; -fx-text-fill: #1A1981; -fx-font-weight: bold;";

    public TextSlider(String sliderName, int minValue, int maxValue)
    {
        Label sliderText = new Label(sliderName);
        sliderText.setStyle(TEXT_STYLE);
        this.slider = new GameSlider(minValue, maxValue);
        this.getChildren().addAll(sliderText, this.slider);
        this.setSpacing(10);
    }

    public int getSliderValue()
    {
        return (int)this.slider.getValue();
    }

    private final GameSlider slider;
}
