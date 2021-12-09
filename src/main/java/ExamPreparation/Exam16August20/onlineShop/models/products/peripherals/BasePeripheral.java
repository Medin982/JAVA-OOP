package ExamPreparation.Exam16August20.onlineShop.models.products.peripherals;

import ExamPreparation.Exam16August20.onlineShop.models.products.BaseProduct;

import static ExamPreparation.Exam16August20.onlineShop.common.constants.OutputMessages.PERIPHERAL_TO_STRING;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {

private String connectionType;

    public BasePeripheral(int id, String manufacturer, String model,
                          double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(PERIPHERAL_TO_STRING, this.connectionType);
    }
}
