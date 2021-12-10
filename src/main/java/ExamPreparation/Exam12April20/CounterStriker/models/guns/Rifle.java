package ExamPreparation.Exam12April20.CounterStriker.models.guns;

public class Rifle extends GunImpl {

    private static final int SHOOTS = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (super.getBulletsCount() < SHOOTS) {
            return 0;
        }
        int reduced = super.getBulletsCount() - SHOOTS;
        super.setBulletsCount(reduced);
        return SHOOTS;
    }
}
