package ExamPreparation.glacialExpedition.models.repositories;

import ExamPreparation.glacialExpedition.models.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collection;

public class ExplorerRepository implements Repository {

    private Collection<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    @Override
    public Collection getCollection() {
        return this.explorers;
    }

    @Override
    public void add(Object explorer) {
        this.explorers.add((Explorer) explorer);
    }

    @Override
    public boolean remove(Object explorer) {
        if (this.explorers.contains(explorer)) {
            this.explorers.remove(explorer);
            return true;
        }
        return false;
    }

    @Override
    public Object byName(String name) {
        return this.explorers.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }
}
