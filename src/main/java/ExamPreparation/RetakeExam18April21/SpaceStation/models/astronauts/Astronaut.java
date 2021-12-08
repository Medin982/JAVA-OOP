package ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts;


import ExamPreparation.RetakeExam18April21.SpaceStation.models.bags.Bag;

public interface Astronaut {
    String getName();

    double getOxygen();

    boolean canBreath();

    Bag getBag();

    void breath();
}
