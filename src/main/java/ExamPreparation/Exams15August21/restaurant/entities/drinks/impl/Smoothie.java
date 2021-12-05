package ExamPreparation.Exams15August21.restaurant.entities.drinks.impl;

public class Smoothie extends BaseBeverage {

    private static final double SMOOTHIE_PRICE = 4.50;

    public Smoothie(String name, int counter, String brand) {
        super(name, counter, SMOOTHIE_PRICE, brand);
    }
}
