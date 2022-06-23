package ru.nsu.ccfit.shishmakov.carfactory.main;

import ru.nsu.ccfit.shishmakov.carfactory.controller.Controller;
import ru.nsu.ccfit.shishmakov.carfactory.model.GameModel;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ConfigConstants;
import ru.nsu.ccfit.shishmakov.carfactory.view.GUI;

public class Main {
    public static void main(String[] args)
    {
        GameModel model = new GameModel();
        Controller controller = new Controller(model);
        model.loadConfig(ConfigConstants.CONFIG_PATH_NAME);
        model.initModel();
        model.startProduction();
        GUI.startGUI(model,controller);
    }
}
