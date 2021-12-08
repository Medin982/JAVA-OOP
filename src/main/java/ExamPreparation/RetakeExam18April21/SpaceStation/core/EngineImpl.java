package ExamPreparation.RetakeExam18April21.SpaceStation.core;

import ExamPreparation.RetakeExam18April21.SpaceStation.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;

    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals(Command.Exit.name())) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddAstronaut:
                result = addAstronaut(data);
                break;
            case AddPlanet:
                result = addPlanet(data);
                break;
            case RetireAstronaut:
                result = retireAstronaut(data);
                break;
            case ExplorePlanet:
                result = explorePlanet(data);
                break;
            case Report:
                result = report();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }

        return result;
    }

    private String retireAstronaut(String[] data) {
        String name = data[0];
        return controller.retireAstronaut(name);
    }

    private String report() {
        return controller.report();
    }

    private String explorePlanet(String[] data) {
        String name = data[0];
        return controller.explorePlanet(name);
    }

    private String addPlanet(String[] data) {
        String name = data[0];
        String[] items = Arrays.stream(data).skip(1).toArray(String[]::new);
        return controller.addPlanet(name, items);
    }

    private String addAstronaut(String[] data) {
        String type = data[0];
        String name = data[1];
        return controller.addAstronaut(type, name);
    }
}
