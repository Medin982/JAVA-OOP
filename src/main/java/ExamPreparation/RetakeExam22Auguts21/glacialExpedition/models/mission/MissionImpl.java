package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.mission;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states.State;

import java.util.List;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, List<Explorer> explorers) {
        for (int i = 0; i < explorers.size(); i++) {
            Explorer currentExplorer = explorers.get(i);
            for (int j = 0; j < state.getExhibits().size(); j++) {
                String currentExhibits = state.getExhibits().get(j);
                currentExplorer.getSuitcase().getExhibits().add(currentExhibits);
                state.getExhibits().remove(currentExhibits);
                j--;
                currentExplorer.search();
                if (!currentExplorer.canSearch()) {
                    explorers.remove(currentExplorer);
                    i--;
                    break;
                }
            }
        }
    }
}
