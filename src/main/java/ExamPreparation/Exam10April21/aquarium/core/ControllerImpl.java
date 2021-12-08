package ExamPreparation.Exam10April21.aquarium.core;

import ExamPreparation.Exam10April21.aquarium.common.ConstantMessages;
import ExamPreparation.Exam10April21.aquarium.common.ExceptionMessages;
import ExamPreparation.Exam10April21.aquarium.entities.aquariums.Aquarium;
import ExamPreparation.Exam10April21.aquarium.entities.aquariums.BaseAquarium;
import ExamPreparation.Exam10April21.aquarium.entities.aquariums.FreshwaterAquarium;
import ExamPreparation.Exam10April21.aquarium.entities.aquariums.SaltwaterAquarium;
import ExamPreparation.Exam10April21.aquarium.entities.decorations.Decoration;
import ExamPreparation.Exam10April21.aquarium.entities.decorations.Ornament;
import ExamPreparation.Exam10April21.aquarium.entities.decorations.Plant;
import ExamPreparation.Exam10April21.aquarium.entities.fish.Fish;
import ExamPreparation.Exam10April21.aquarium.entities.fish.FreshwaterFish;
import ExamPreparation.Exam10April21.aquarium.entities.fish.SaltwaterFish;
import ExamPreparation.Exam10April21.aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        this.decorations.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration insertDecoration = this.decorations.findByType(decorationType);
        if (insertDecoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        Aquarium aquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst().orElse(null);
        assert aquarium != null;
        aquarium.addDecoration(insertDecoration);
        this.decorations.remove(insertDecoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        BaseAquarium aquarium = (BaseAquarium) this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst().orElse(null);

        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        assert aquarium != null;

        try {
            aquarium.addFish(fish);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

//        if (!aquarium.getClass().getSimpleName().equals(fish.getLiveAquarium())) {
//            return ConstantMessages.WATER_NOT_SUITABLE;
//        }
        switch (fishType) {
            case "FreshwaterFish":
                if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")) {
                    return ConstantMessages.WATER_NOT_SUITABLE;
                }
                break;
            case "SaltwaterFish":
                if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium")) {
                    return ConstantMessages.WATER_NOT_SUITABLE;
                }
                break;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        assert aquarium != null;
        aquarium.feed();
        return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);

        assert aquarium != null;
        double fishSum = aquarium.getFish()
                .stream()
                .mapToDouble(Fish::getPrice)
                .sum();

        double decorationsSum = aquarium.getDecorations()
                .stream()
                .mapToDouble(Decoration::getPrice)
                .sum();

        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, fishSum + decorationsSum);
    }

    @Override
    public String report() {
        StringBuilder report = new StringBuilder();
        this.aquariums.forEach(aquarium -> report.append(aquarium.getInfo()).append(System.lineSeparator()));
        return report.toString().trim();
    }
}
