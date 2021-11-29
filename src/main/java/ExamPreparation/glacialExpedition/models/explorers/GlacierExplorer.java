package ExamPreparation.glacialExpedition.models.explorers;

import ExamPreparation.glacialExpedition.Impl.BaseExplorer;

public class GlacierExplorer extends BaseExplorer {

    private final static double GLACIER_EXPLORER_ENERGY = 40;


    public GlacierExplorer(String name) {
        super(name, GLACIER_EXPLORER_ENERGY);
    }
}
