package ExamPreparation.glacialExpedition.models.models.explorers;

import ExamPreparation.glacialExpedition.models.Impl.BaseExplorerImpl;

public class GlacierExplorer extends BaseExplorerImpl {

    private final static double GLACIER_EXPLORER_ENERGY = 40;


    public GlacierExplorer(String name) {
        super(name, GLACIER_EXPLORER_ENERGY);
    }
}
