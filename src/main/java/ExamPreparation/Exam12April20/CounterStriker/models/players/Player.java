package ExamPreparation.Exam12April20.CounterStriker.models.players;

import ExamPreparation.Exam12April20.CounterStriker.models.guns.Gun;

public interface Player {
    String getUsername();

    int getHealth();

    int getArmor();

    Gun getGun();

    boolean isAlive();

    void takeDamage(int points);
}
