package ExamPreparation.glacialExpedition.models.Impl;


import ExamPreparation.glacialExpedition.models.common.ExceptionMessages;
import ExamPreparation.glacialExpedition.models.models.explorers.Explorer;
import ExamPreparation.glacialExpedition.models.models.suitcases.Suitcase;

public class BaseExplorerImpl implements Explorer {

    private final static int DECREASE_ENERGY = 15;

    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorerImpl(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
    }

    protected void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    private void setName(String name) {
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
        if (this.energy > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy -= DECREASE_ENERGY;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }
}
