package ExamPreparation.RetakeExam18April21.SpaceStation.models.mission;

import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Astronaut;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.planets.Planet;

import java.util.List;

public class MissionImpl implements Mission {

    public MissionImpl() {
    }

    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {
        for (int k = 0; k < astronauts.size(); k++) {
            for (int i = 0; i < planet.getItems().size(); i++) {
                if (astronauts.get(k).canBreath()) {
                    astronauts.get(k).breath();
                    astronauts.get(k).getBag().getItems().add(planet.getItems().get(i));
                    planet.getItems().remove(i);
                    i--;
                } else {
                    astronauts.remove(k);
                    k--;
                    break;
                }
            }
        }
    }
}

