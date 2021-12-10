package ExamPreparation.Exam12April20.CounterStriker.repositories;

import ExamPreparation.Exam12April20.CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.Exam12April20.CounterStriker.common.ExceptionMessages.INVALID_PLAYER_REPOSITORY;

public class PlayerRepository implements Repository<Player> {

    private Collection<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.models;
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return this.models.remove(model);
    }

    @Override
    public Player findByName(String name) {
        return this.models.stream()
                .filter(p -> p.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }
}
