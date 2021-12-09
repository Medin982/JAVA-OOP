package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers;

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
        if (!canSearch()) {
            this.setEnergy(0);
        }
        this.setEnergy(currentEnergy);
    }
}