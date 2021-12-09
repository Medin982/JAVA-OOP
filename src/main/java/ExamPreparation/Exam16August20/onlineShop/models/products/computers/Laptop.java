package ExamPreparation.Exam16August20.onlineShop.models.products.computers;

public class Laptop extends BaseComputer {

    private static final double overall_Performance = 10;

    public Laptop(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, overall_Performance);
    }
}
