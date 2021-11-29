package ExamPreparation.glacialExpedition.repositories;

import ExamPreparation.glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;

public class StateRepository implements Repository<State> {

    private Collection<State> states;
    private int countExplorerState;

    public StateRepository() {
        this.states = new ArrayList<>();
        this.countExplorerState = 0;
    }

    public void setCountExplorerState(int countExplorerState) {
        this.countExplorerState = countExplorerState;
    }

    public int getCountExplorerState() {
        return countExplorerState;
    }

    @Override
    public Collection getCollection() {
        return this.states;
    }

    @Override
    public void add(State state) {
        this.states.add(state);
    }

    @Override
    public boolean remove(State state) {
        if (this.states.contains(state)) {
                this.states.remove(state);
                return true;
            }
        return false;
    }

    @Override
    public State byName(String name) {
        return this.states.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst().orElse(null);
    }
}
