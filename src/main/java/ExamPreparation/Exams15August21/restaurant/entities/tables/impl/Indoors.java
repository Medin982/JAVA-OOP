package ExamPreparation.Exams15August21.restaurant.entities.tables.impl;

public class Indoors extends BaseTable {

    private static final double PRICE_PER_PERSON = 3.50;

    public Indoors(int number, int size) {
        super(number, size, PRICE_PER_PERSON);
    }
}
