package ExamPreparation.Exam16August20.onlineShop.models.products.components;

import ExamPreparation.Exam16August20.onlineShop.models.products.BaseProduct;

import static ExamPreparation.Exam16August20.onlineShop.common.constants.OutputMessages.COMPONENT_TO_STRING;

public abstract class BaseComponent extends BaseProduct implements Component {

    private int generation;

    protected BaseComponent(int id, String manufacturer, String model, double price,
                         double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(COMPONENT_TO_STRING, this.generation);
    }
}
