package ExamPreparation.Exam16August20.onlineShop.models.products;

import static ExamPreparation.Exam16August20.onlineShop.common.constants.ExceptionMessages.*;
import static ExamPreparation.Exam16August20.onlineShop.common.constants.OutputMessages.PRODUCT_TO_STRING;

public abstract class BaseProduct implements Product {

    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    protected BaseProduct(int id, String manufacturer, String model, double price,
                          double overallPerformance) {
       this.setId(id);
       this.setManufacturer(manufacturer);
       this.setModel(model);
       this.setPrice(price);
       this.setOverallPerformance(overallPerformance);
    }

    private void setOverallPerformance(double overallPerformance) {
        if (validationDouble(overallPerformance)) {
            throw new IllegalArgumentException(INVALID_OVERALL_PERFORMANCE);
        }
        this.overallPerformance = overallPerformance;
    }

    private void setPrice(double price) {
        if (validationDouble(price)) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    private void setModel(String model) {
        if (validationStrings(model)) {
            throw new IllegalArgumentException(INVALID_MODEL);
        }
        this.model = model;
    }

    private void setManufacturer(String manufacturer) {
        if (validationStrings(manufacturer)) {
            throw new IllegalArgumentException(INVALID_MANUFACTURER);
        }
        this.manufacturer = manufacturer;
    }

    private void setId(int id) {
        if (validationDouble(id)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_ID);
        }
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getOverallPerformance() {
        return this.overallPerformance;
    }

    @Override
    public String toString() {
        return String.format(PRODUCT_TO_STRING,
                this.overallPerformance, this.price, this.getClass().getSimpleName(),
                this.manufacturer, this.model, this.id);
    }

    private boolean validationStrings(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean validationDouble(double value) {
        return value <= 0;
    }
}
