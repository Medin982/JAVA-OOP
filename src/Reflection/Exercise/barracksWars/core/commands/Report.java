package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Report extends Command {

    public Report(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String output = getRepository().getStatistics();
        return output;
    }
}
