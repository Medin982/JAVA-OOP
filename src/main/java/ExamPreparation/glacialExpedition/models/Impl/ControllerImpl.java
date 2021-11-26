package ExamPreparation.glacialExpedition.models.Impl;

import ExamPreparation.glacialExpedition.models.common.ExceptionMessages;
import ExamPreparation.glacialExpedition.models.core.Controller;
import ExamPreparation.glacialExpedition.models.enums.Explorers;
import ExamPreparation.glacialExpedition.models.models.explorers.AnimalExplorer;
import ExamPreparation.glacialExpedition.models.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.models.explorers.GlacierExplorer;
import ExamPreparation.glacialExpedition.models.models.explorers.NaturalExplorer;
import ExamPreparation.glacialExpedition.models.models.states.State;
import ExamPreparation.glacialExpedition.models.repositories.ExplorerRepository;
import ExamPreparation.glacialExpedition.models.repositories.StateRepository;

import java.util.List;
import java.util.function.Predicate;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;

    @Override
    public String addExplorer(String type, String explorerName) {
        try {
            if (type.equalsIgnoreCase(String.valueOf(Explorers.ANIMAL_EXPLORER))) {
                Explorer explorer = new AnimalExplorer(explorerName);
               this.explorerRepository.add(explorer);
            } else if (type.equalsIgnoreCase(String.valueOf(Explorers.GLACIER_EXPLORER))) {
                Explorer explorer = new GlacierExplorer(explorerName);
                this.explorerRepository.add(explorer);
            } else if (type.equalsIgnoreCase(String.valueOf(Explorers.NATURAL_EXPLORER))) {
                Explorer explorer = new NaturalExplorer(explorerName);
               this.explorerRepository.add(explorer);
            }
        }catch (IllegalArgumentException e) {
            System.out.println(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        return String.format("Added %s: %s", type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        this.stateRepository.add(state);
        return String.format("Added state: %s.", stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
       try {
           stateRepository.remove(explorerName);
           return String.format("Explorer %s has retired!",explorerName);
       } catch (IllegalArgumentException e) {
            return String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName);
       }
    }

    @Override
    public String exploreState(String stateName) {
            Predicate<Double> predicate = num -> num > 50;
            List<Explorer> explorerList = this.explorerRepository.getCollection().stream().toList();
            explorerList = explorerList.stream().filter(explorer -> predicate.test(explorer.getEnergy())).toList();
            if (explorerList.isEmpty()) {
                throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
            }
            explorerList.forEach(explorer -> {
                explorer.search();
                if (explorer.canSearch()) {
//                    explorer.getSuitcase().
                }
            });

        return null;
    }

    @Override
    public String finalResult() {
        return null;
    }
}
