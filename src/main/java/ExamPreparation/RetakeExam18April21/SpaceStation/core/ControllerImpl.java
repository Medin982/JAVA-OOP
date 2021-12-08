package ExamPreparation.RetakeExam18April21.SpaceStation.core;

import ExamPreparation.RetakeExam18April21.SpaceStation.common.ConstantMessages;
import ExamPreparation.RetakeExam18April21.SpaceStation.common.ExceptionMessages;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Astronaut;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Biologist;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Geodesist;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.astronauts.Meteorologist;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.mission.Mission;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.mission.MissionImpl;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.planets.Planet;
import ExamPreparation.RetakeExam18April21.SpaceStation.repositories.AstronautRepository;
import ExamPreparation.RetakeExam18April21.SpaceStation.repositories.PlanetRepository;
import ExamPreparation.RetakeExam18April21.SpaceStation.models.planets.PlanetImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanet = 0;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        this.planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) throws IllegalArgumentException {
        Astronaut retired = this.astronautRepository.findByName(astronautName);
        if (retired == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        this.astronautRepository.remove(retired);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> astronauts = this.astronautRepository.getModels().stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());
        if (astronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet explorerPlanet = this.planetRepository.findByName(planetName);
        int beforeMission = astronauts.size();
        Mission mission = new MissionImpl();
        mission.explore(explorerPlanet, astronauts);
        this.exploredPlanet++;
        int afterMission = astronauts.size();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, beforeMission - afterMission);
    }

    @Override
    public String report() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, this.exploredPlanet))
                .append(System.lineSeparator())
                .append(ConstantMessages.REPORT_ASTRONAUT_INFO)
                .append(System.lineSeparator());
        List<Astronaut> astronauts = this.astronautRepository.getModels();
        for (Astronaut astronaut : astronauts) {
            result.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, astronaut.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()))
                    .append(System.lineSeparator());
                    String bagItems;
                    if (astronaut.getBag().getItems().isEmpty()) {
                        bagItems = "none";
                    } else {
                        bagItems = String.join(", ", astronaut.getBag().getItems());
                    }
                    result.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, bagItems))
                            .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
