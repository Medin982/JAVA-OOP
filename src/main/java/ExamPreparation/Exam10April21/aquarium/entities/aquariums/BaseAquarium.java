package ExamPreparation.Exam10April21.aquarium.entities.aquariums;

import ExamPreparation.Exam10April21.aquarium.common.ConstantMessages;
import ExamPreparation.Exam10April21.aquarium.common.ExceptionMessages;
import ExamPreparation.Exam10April21.aquarium.entities.decorations.Decoration;
import ExamPreparation.Exam10April21.aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (fish.getSize() >= this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish f : this.fish) {
            f.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s (%s): ", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator());

        String fishInfo;

        if (this.fish.isEmpty()) {
            fishInfo = "none";
        } else {
            fishInfo = this.fish.stream().map(Fish::getName).collect(Collectors.joining(", "));
        }

        builder.append(String.format("Fish: %s", fishInfo))
                .append(System.lineSeparator())
                .append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator())
                .append(String.format("Comfort: %d", this.calculateComfort()));
        return builder.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
