package ExamPreparation.RetakeExam19Dec20.viceCity;

import ExamPreparation.RetakeExam19Dec20.viceCity.core.ControllerImpl;
import ExamPreparation.RetakeExam19Dec20.viceCity.core.EngineImpl;
import ExamPreparation.RetakeExam19Dec20.viceCity.core.interfaces.Controller;
import ExamPreparation.RetakeExam19Dec20.viceCity.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
