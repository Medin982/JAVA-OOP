package ExamPreparation.Exams15August21.restaurant.entities.tables.interfaces;

import ExamPreparation.Exams15August21.restaurant.entities.drinks.interfaces.Beverages;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.interfaces.HealthyFood;

public interface Table {
    int getTableNumber();

    int getSize();

    int numberOfPeople();

    double pricePerPerson();

    boolean isReservedTable();

    double allPeople();

    void reserve(int numberOfPeople);

    void orderHealthy(HealthyFood food);

    void orderBeverages(Beverages beverages);

    double bill();

    void clear();

    String tableInformation();
}
