package ExamPreparation.glacialExpedition.models.explorers;

import ExamPreparation.glacialExpedition.Impl.BaseExplorer;

public class AnimalExplorer extends BaseExplorer {

    private final static double ANIMAL_EXPLORER_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, ANIMAL_EXPLORER_ENERGY);
    }
}
