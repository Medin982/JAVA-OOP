package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.mission;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states.State;

import java.util.List;

public interface Mission {
    void explore(State state, List<Explorer> explorers);
}
