package ExamPreparation.Exam16August20.onlineShop.models.products.computers;

public class DesktopComputer extends BaseComputer {

    private static final double overall_Performance = 15;

    public DesktopComputer(int id, String manufacturer, String model,
                           double price) {
        super(id, manufacturer, model, price, overall_Performance);
    }
}
