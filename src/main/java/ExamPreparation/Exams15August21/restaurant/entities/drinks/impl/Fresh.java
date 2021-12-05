package ExamPreparation.Exams15August21.restaurant.entities.drinks.impl;

public class Fresh extends BaseBeverage {

    private static final double FRESH_PRICE = 4.50;

    public Fresh(String name, int counter, String brand) {
        super(name, counter, FRESH_PRICE, brand);
    }
}
