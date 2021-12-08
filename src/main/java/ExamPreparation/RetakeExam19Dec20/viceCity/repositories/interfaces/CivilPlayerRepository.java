package ExamPreparation.RetakeExam19Dec20.viceCity.repositories.interfaces;

import ExamPreparation.RetakeExam19Dec20.viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.List;

public class CivilPlayerRepository implements Repository<Player> {

    private List<Player> models;

    public CivilPlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public List<Player> getModels() {
        return this.models;
    }

    @Override
    public void add(Player model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return this.models.remove(model);
    }

    @Override
    public Player find(String name) {
        return this.models.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
