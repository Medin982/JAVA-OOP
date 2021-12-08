package ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.explorers;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.models.suitcases.Suitcase;

public interface Explorer {
    String getName();

    double getEnergy();

    boolean canSearch();

    Suitcase getSuitcase();

    void search();
}
