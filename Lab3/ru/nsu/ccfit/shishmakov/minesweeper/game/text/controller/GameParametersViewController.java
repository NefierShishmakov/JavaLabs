package ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller;

import javafx.scene.control.TextField;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.context.Context;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.slider.TextSlider;

import java.util.ArrayList;

public class GameParametersViewController {
    public GameParametersViewController(TextField nameTextField, ArrayList<TextSlider> sliders)
    {
        this.nameTextField = nameTextField;
        this.sliders = sliders;
    }

    public boolean isNicknameTextFieldEmpty()
    {
        return this.nameTextField.getText().isEmpty();
    }

    public Context getContext()
    {
        ArrayList<Integer> fieldParameters = new ArrayList<>();

        for (TextSlider slider: sliders)
        {
            fieldParameters.add(slider.getSliderValue());
        }

        return new Context(nameTextField.getText(), fieldParameters);
    }

    private final TextField nameTextField;
    private final ArrayList<TextSlider> sliders;
}
