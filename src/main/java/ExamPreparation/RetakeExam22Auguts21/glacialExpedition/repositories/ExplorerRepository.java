package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.repositories;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorerRepository implements Repository<Explorer> {

    private List<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    public void setExplorers(List<Explorer> explorers) {
        this.explorers = explorers;
    }

    @Override
    public List<Explorer> getCollection() {
        return Collections.unmodifiableList(this.explorers);
    }

    @Override
    public void add(Explorer entity) {
        this.explorers.add(entity);
    }

    @Override
    public boolean remove(Explorer entity) {
          return this.explorers.remove(entity);

    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }
}
