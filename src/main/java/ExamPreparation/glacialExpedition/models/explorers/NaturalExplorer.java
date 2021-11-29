package ExamPreparation.glacialExpedition.models.explorers;

import ExamPreparation.glacialExpedition.Impl.BaseExplorer;

public class NaturalExplorer extends BaseExplorer {

    private final static double NATURAL_EXPLORER_ENERGY = 60;
    private final static int DECREASE_ENERGY = 7;

    public NaturalExplorer(String name) {
        super(name, NATURAL_EXPLORER_ENERGY);
    }

    @Override
    public void search() {
        double currentEnergy = this.getEnergy();
        currentEnergy -= DECREASE_ENERGY;
        if (currentEnergy < 0 ) {
            this.setEnergy(0);
        }
        this.setEnergy(currentEnergy);
    }
}
