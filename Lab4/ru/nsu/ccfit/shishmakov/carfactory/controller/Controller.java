package ru.nsu.ccfit.shishmakov.carfactory.controller;

import ru.nsu.ccfit.shishmakov.carfactory.model.GameModel;
import ru.nsu.ccfit.shishmakov.carfactory.utils.command.Command;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ErrorConstants;

public class Controller {
    public Controller(GameModel model)
    {
        assert model != null;
        this.model = model;
    }

    public void executeCommand(Command command){
        Command.CommandType type = command.getType();

        switch (type)
        {
            case BODY_DELAY -> this.model.setSupplierBodyDelay(command.getValue());
            case ENGINE_DELAY -> this.model.setSupplierEngineDelay(command.getValue());
            case ACCESSORY_DELAY -> this.model.setSupplierAccessoryDelay(command.getValue());
            case DEALER_DELAY -> this.model.setDealersDelay(command.getValue());
            case INCORRECT -> System.err.println(ErrorConstants.CONTROLLER_COMMAND_ERROR);
        }
    }

    private final GameModel model;
}
