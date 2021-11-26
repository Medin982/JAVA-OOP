package ExamPreparation.glacialExpedition.models.models.mission;

import ExamPreparation.glacialExpedition.models.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.models.states.State;


import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
