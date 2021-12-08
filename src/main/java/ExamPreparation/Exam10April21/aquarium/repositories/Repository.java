package ExamPreparation.Exam10April21.aquarium.repositories;

import ExamPreparation.Exam10April21.aquarium.entities.decorations.Decoration;

public interface Repository  {
    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
