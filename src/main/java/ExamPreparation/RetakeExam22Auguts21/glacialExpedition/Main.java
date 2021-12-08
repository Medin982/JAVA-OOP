package ExamPreparation.RetakeExam22Auguts21.glacialExpedition;

import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.core.Controller;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.core.ControllerImpl;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.core.Engine;
import ExamPreparation.RetakeExam22Auguts21.glacialExpedition.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
