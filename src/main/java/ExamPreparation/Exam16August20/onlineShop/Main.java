package ExamPreparation.Exam16August20.onlineShop;

import ExamPreparation.Exam16August20.onlineShop.core.EngineImpl;
import ExamPreparation.Exam16August20.onlineShop.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
