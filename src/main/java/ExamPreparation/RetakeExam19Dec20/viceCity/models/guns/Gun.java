package ExamPreparation.RetakeExam19Dec20.viceCity.models.guns;

public interface Gun {
    String getName();

    int getBulletsPerBarrel();

    boolean canFire();

    int getTotalBullets();

    int fire();
}
