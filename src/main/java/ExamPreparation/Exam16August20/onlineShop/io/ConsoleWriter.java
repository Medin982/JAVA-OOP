package ExamPreparation.Exam16August20.onlineShop.io;


import ExamPreparation.Exam16August20.onlineShop.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
