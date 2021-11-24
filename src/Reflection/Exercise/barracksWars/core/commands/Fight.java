package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Fight extends Command {

    public Fight(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        return "fight";
    }
}
