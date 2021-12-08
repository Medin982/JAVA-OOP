package ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    private static final double OXYGEN = 70;

    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        double current = super.getOxygen();
        current -= 5;
        if (current <= 0) {
            super.setOxygen(0);
        } else {
            super.setOxygen(current);
        }
    }
}
