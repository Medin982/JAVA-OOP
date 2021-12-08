package ExamPreparation.RetakeExam19Dec20.viceCity.models.players;

import ExamPreparation.RetakeExam19Dec20.viceCity.models.guns.Gun;
import ExamPreparation.RetakeExam19Dec20.viceCity.repositories.interfaces.Repository;

public interface Player {
    String getName();

    int getLifePoints();

    boolean isAlive();

    Repository<Gun> getGunRepository();

    void takeLifePoints(int points);
}
