package ExamPreparation.glacialExpedition.Impl;

import ExamPreparation.glacialExpedition.common.ExceptionMessages;
import ExamPreparation.glacialExpedition.core.Controller;
import ExamPreparation.glacialExpedition.models.explorers.AnimalExplorer;
import ExamPreparation.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.explorers.GlacierExplorer;
import ExamPreparation.glacialExpedition.models.explorers.NaturalExplorer;
import ExamPreparation.glacialExpedition.models.mission.Mission;
import ExamPreparation.glacialExpedition.models.states.State;
import ExamPreparation.glacialExpedition.repositories.ExplorerRepository;
import ExamPreparation.glacialExpedition.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private static final int MIN_ENERGY_FOR_SEARCH = 50;

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
            if (type.equalsIgnoreCase("AnimalExplorer")) {
                Explorer explorer = new AnimalExplorer(explorerName);
                this.explorerRepository.add(explorer);
            } else if (type.equalsIgnoreCase("GlacierExplorer")) {
                Explorer explorer = new GlacierExplorer(explorerName);
                this.explorerRepository.add(explorer);
            } else if (type.equalsIgnoreCase("NaturalExplorer")) {
                Explorer explorer = new NaturalExplorer(explorerName);
                this.explorerRepository.add(explorer);
            } else {
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
            }
        return String.format("Added %s: %s.", type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        this.stateRepository.add(state);
        return String.format("Added state: %s.", stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_DOES_NOT_EXIST);
        } else {
            this.explorerRepository.remove(explorer);
            return String.format("Explorer %s has retired!", explorerName);
        }
    }

    @Override
    public String exploreState(String stateName) {
        Mission mission = new MissionImpl();
        List<Explorer> explorerList = (List<Explorer>) this.explorerRepository.getCollection().stream().collect(Collectors.toList());
        explorerList = explorerList.stream().filter(explorer -> explorer.getEnergy() > MIN_ENERGY_FOR_SEARCH).collect(Collectors.toList());
        mission.explore((State) this.stateRepository.byName(stateName), explorerList);
        State currentState = (State) stateRepository.byName(stateName);
        return (String.format("The state %s was explored. %d researchers have retired on this mission.",
                stateName, explorerRepository.getRetiredExplorer()));
    }

    @Override
    public String finalResult() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%d states were explored.", stateRepository.getCountExplorerState()))
                .append(System.lineSeparator())
                .append("Information for the explorers:")
                .append(System.lineSeparator());
        List<Explorer> list = (List<Explorer>) explorerRepository.getCollection().stream().collect(Collectors.toList());
        for (Explorer explorer : list) {
            result.append(String.format("Name: %s%n" +
                            "Energy: %.0f", explorer.getName(), explorer.getEnergy()))
                    .append(System.lineSeparator())
                    .append("Suitcase exhibits: ");
            result.append(String.join(", ", explorer.getSuitcase().getExhibits()))
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
