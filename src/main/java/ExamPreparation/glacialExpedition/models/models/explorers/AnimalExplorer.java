package ExamPreparation.glacialExpedition.models.models.explorers;

import ExamPreparation.glacialExpedition.models.Impl.BaseExplorerImpl;

public class AnimalExplorer extends BaseExplorerImpl {

    private final static double ANIMAL_EXPLORER_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, ANIMAL_EXPLORER_ENERGY);
    }
}
