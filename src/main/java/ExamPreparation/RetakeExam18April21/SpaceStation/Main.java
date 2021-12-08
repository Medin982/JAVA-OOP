package ExamPreparation.RetakeExam18April21.SpaceStation;


import ExamPreparation.RetakeExam18April21.SpaceStation.core.Controller;
import ExamPreparation.RetakeExam18April21.SpaceStation.core.ControllerImpl;
import ExamPreparation.RetakeExam18April21.SpaceStation.core.Engine;
import ExamPreparation.RetakeExam18April21.SpaceStation.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
