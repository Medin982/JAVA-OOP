package ExamPreparation.glacialExpedition.Impl;

import ExamPreparation.glacialExpedition.common.ExceptionMessages;
import ExamPreparation.glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;

public class StateImpl implements State {

    private String name;
    private Collection<String> exhibits;

    public StateImpl(String name) {
        this.setName(name);
        this.exhibits = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.STATE_NAME_NULL_OR_EMPTY);
        }
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
