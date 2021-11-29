package ExamPreparation.glacialExpedition;

import ExamPreparation.glacialExpedition.core.Controller;
import ExamPreparation.glacialExpedition.Impl.ControllerImpl;
import ExamPreparation.glacialExpedition.core.Engine;
import ExamPreparation.glacialExpedition.Impl.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
