package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.suitcases.Carton;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.suitcases.Suitcase;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.common.ExceptionMessages;

public abstract class BaseExplorer implements Explorer {

    private final static int DECREASE_ENERGY = 15;

    private String name;
    private double energy;
    private Suitcase suitcase;

    protected BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();
    }

    protected void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canSearch() {
        return this.energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy -= DECREASE_ENERGY;
        if (!canSearch()) {
            this.energy = 0;
        }
    }
}
