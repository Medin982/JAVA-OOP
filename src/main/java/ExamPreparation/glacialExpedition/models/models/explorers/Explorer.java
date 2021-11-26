package ExamPreparation.glacialExpedition.models.models.explorers;

import ExamPreparation.glacialExpedition.models.models.suitcases.Suitcase;

public interface Explorer {
    String getName();

    double getEnergy();

    boolean canSearch();

    Suitcase getSuitcase();

    void search();
}
