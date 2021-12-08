package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.core;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.AnimalExplorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.GlacierExplorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.NaturalExplorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.repositories.ExplorerRepository;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.repositories.StateRepository;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.mission.MissionImpl;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states.StateImpl;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.common.ConstantMessages;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.common.ExceptionMessages;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.mission.Mission;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states.State;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private static final int MIN_ENERGY_FOR_SEARCH = 50;

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private static int exploredStates = 0;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        if (type.equals("AnimalExplorer")) {
            Explorer explorer = new AnimalExplorer(explorerName);
            this.explorerRepository.add(explorer);
        } else if (type.equals("GlacierExplorer")) {
            Explorer explorer = new GlacierExplorer(explorerName);
            this.explorerRepository.add(explorer);
        } else if (type.equals("NaturalExplorer")) {
            Explorer explorer = new NaturalExplorer(explorerName);
            this.explorerRepository.add(explorer);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        this.explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorers = explorerRepository.getCollection().stream()
                .filter(e -> e.getEnergy() > MIN_ENERGY_FOR_SEARCH).collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        int countBefore = explorers.size();
        Mission mission = new MissionImpl();
        State state = this.stateRepository.byName(stateName);
        mission.explore(state, explorers);
        exploredStates++;
        int countAfter = explorers.size();
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, Math.abs(countBefore - countAfter));
    }

    @Override
    public String finalResult() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploredStates))
                .append(System.lineSeparator())
                .append(ConstantMessages.FINAL_EXPLORER_INFO)
                .append(System.lineSeparator());
        List<Explorer> list = explorerRepository.getCollection();
        for (Explorer explorer : list) {
            result.append(String.format("Name: %s%n" +
                            "Energy: %.0f", explorer.getName(), explorer.getEnergy()))
                    .append(System.lineSeparator())
                    .append("Suitcase exhibits: ");
            String exhibits = "";
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                exhibits = "None";
            } else {
                exhibits = String.join(", ", explorer.getSuitcase().getExhibits());
            }
            result.append(exhibits).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
