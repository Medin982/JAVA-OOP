package Reflection.Exercise.barracksWars.core;

import Reflection.Exercise.barracksWars.core.commands.Command;
import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.Runnable;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Engine implements Runnable {

    private static final String COMMANDS_PATH_NAME = "Reflection.Exercise.barracksWars.core.commands.";
    private static final String EXECUTE_METHOD_NAME = "execute";

    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ExecutionControl.NotImplementedException e) {
                e.printStackTrace();
            }
        }
    }

    private String interpretCommand(String[] data, String commandName) throws ExecutionControl.NotImplementedException {
        String result = "";
        commandName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
        try {
            Class commandClass = Class.forName(COMMANDS_PATH_NAME + commandName);
            Constructor<Command> constructor = commandClass.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
            Command commandInstance = constructor.newInstance(data, this.repository, this.unitFactory);
            Method method = commandClass.getDeclaredMethod(EXECUTE_METHOD_NAME);
            try {
                result = (String) method.invoke(commandInstance);
            } catch (InvocationTargetException e) {
               return e.getCause().getMessage();
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

}
