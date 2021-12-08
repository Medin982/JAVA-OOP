package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;

public class StateImpl implements State {

    private String name;
    private List<String> exhibits;

    public StateImpl(String name) {
        this.setName(name);
        this.exhibits = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.STATE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
