package ExamPreparation.glacialExpedition.models.Impl;

import ExamPreparation.glacialExpedition.models.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.models.mission.Mission;
import ExamPreparation.glacialExpedition.models.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        for (Explorer explorer : explorers) {
            if (explorer.canSearch()) {
                continue;
            }
            explorer.search();
        }
    }
}
