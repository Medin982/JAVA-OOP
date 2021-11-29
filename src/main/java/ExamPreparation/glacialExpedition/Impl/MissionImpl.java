package ExamPreparation.glacialExpedition.Impl;

import ExamPreparation.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.mission.Mission;
import ExamPreparation.glacialExpedition.models.states.State;
import ExamPreparation.glacialExpedition.models.suitcases.Carton;
import ExamPreparation.glacialExpedition.repositories.ExplorerRepository;
import ExamPreparation.glacialExpedition.repositories.StateRepository;

import java.util.Collection;

public class MissionImpl implements Mission {

    private static final int MIN_ENERGY_FOR_SEARCH = 50;

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Carton carton = new Carton();
        StateRepository stateRepository = new StateRepository();
        ExplorerRepository explorerRepository = new ExplorerRepository();
        for (Explorer explorer : explorers) {
            boolean isExplorerState = false;
            if (explorer.canSearch()) {
                explorer.search();
                if (explorer.getEnergy() < MIN_ENERGY_FOR_SEARCH) {
                    int currentRetiredExporer = explorerRepository.getRetiredExplorer();
                    currentRetiredExporer++;
                    explorerRepository.setRetiredExplorer(currentRetiredExporer);
                    continue;
                }
                state.getExhibits().remove(state.getName());
                carton.getExhibits().add(state.getName());
                isExplorerState = true;
                int currentExplorerState = stateRepository.getCountExplorerState();
                currentExplorerState++;
                stateRepository.setCountExplorerState(currentExplorerState);
            }
            if (isExplorerState) {
                break;
            }
        }
    }
}
