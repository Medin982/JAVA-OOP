package ExamPreparation.RetakeExam18April21.SpaceStation.models.mission;

import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Astronaut;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.planets.Planet;

import java.util.List;


public interface Mission {
    void explore(Planet planet, List<Astronaut> astronauts);
}
