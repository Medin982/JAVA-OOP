package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.repositories;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateRepository implements Repository<State> {

    private List<State> states;

    public StateRepository() {
        this.states = new ArrayList<>();
    }

    @Override
    public List<State> getCollection() {
        return Collections.unmodifiableList(this.states);
    }

    @Override
    public void add(State state) {
        this.states.add(state);
    }

    @Override
    public boolean remove(State state) {
       return this.states.remove(state);
    }

    @Override
    public State byName(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
