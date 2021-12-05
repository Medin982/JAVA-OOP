package ExamPreparation.Exams15August21.restaurant.io;

import ExamPreparation.Exams15August21.restaurant.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
