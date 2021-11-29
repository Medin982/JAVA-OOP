package ExamPreparation.glacialExpedition.repositories;

import ExamPreparation.glacialExpedition.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collection;

public class ExplorerRepository implements Repository<Explorer> {

    private Collection<Explorer> explorers;
    private int retiredExplorer;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
        this.retiredExplorer = 0;
    }

    public int getRetiredExplorer() {
        return retiredExplorer;
    }

    public void setRetiredExplorer(int retiredExplorer) {
        this.retiredExplorer = retiredExplorer;
    }

    @Override
    public Collection getCollection() {
        return this.explorers;
    }

    @Override
    public void add(Explorer explorer) {
        this.explorers.add((Explorer) explorer);
    }

    @Override
    public boolean remove(Explorer explorer) {
        if (this.explorers.contains(explorer)) {
            this.explorers.remove(explorer);
            return true;
        }
        return false;
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }
}
