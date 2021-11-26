package ExamPreparation.glacialExpedition.models;

import ExamPreparation.glacialExpedition.models.core.Controller;
import ExamPreparation.glacialExpedition.models.Impl.ControllerImpl;
import ExamPreparation.glacialExpedition.models.core.Engine;
import ExamPreparation.glacialExpedition.models.Impl.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
