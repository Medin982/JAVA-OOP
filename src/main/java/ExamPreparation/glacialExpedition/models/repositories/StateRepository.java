package ExamPreparation.glacialExpedition.models.repositories;

import ExamPreparation.glacialExpedition.models.models.states.State;

import java.util.Collection;

public class StateRepository implements Repository {

    private Collection<State> states;

    @Override
    public Collection getCollection() {
        return this.states;
    }

    @Override
    public void add(Object state) {
        this.states.add((State) state);
    }

    @Override
    public boolean remove(Object state) {
        if (this.states.contains(state)) {
            this.states.remove(state);
            return true;
        }
        return false;
    }

    @Override
    public Object byName(String name) {
        return this.states.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst().orElse(null);
    }
}
