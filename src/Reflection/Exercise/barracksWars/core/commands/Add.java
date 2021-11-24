package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.Unit;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command {

    public Add(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String unitType = getData()[1];
        Unit unitToAdd = getUnitFactory().createUnit(unitType);
        getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
