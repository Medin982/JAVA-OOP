package ExamPreparation.glacialExpedition.models.mission;

import ExamPreparation.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.states.State;


import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
