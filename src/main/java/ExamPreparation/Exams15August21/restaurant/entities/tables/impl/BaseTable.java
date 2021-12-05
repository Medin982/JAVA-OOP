package ExamPreparation.Exams15August21.restaurant.entities.tables.impl;

import ExamPreparation.Exams15August21.restaurant.common.ExceptionMessages;
import ExamPreparation.Exams15August21.restaurant.entities.drinks.interfaces.Beverages;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import ExamPreparation.Exams15August21.restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public class BaseTable implements Table {

    private int number;
    private int size;
    private double pricePerPerson;
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int numberOfPeople;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.isReservedTable = false;
    }


    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        this.setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double sumFoodPrice = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double sumBeveragesPrice = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        this.allPeople = this.numberOfPeople * this.pricePerPerson + sumFoodPrice + sumBeveragesPrice;
        return this.allPeople;
    }

    @Override
    public void clear() {
        this.healthyFood.clear();
        this.beverages.clear();
        this.setNumberOfPeople(0);
        this.allPeople = 0;
        this.isReservedTable = false;
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d", this.number))
                .append(System.lineSeparator())
                .append(String.format("Size - %d", this.size))
                .append(System.lineSeparator())
                .append(String.format("Type - %s", getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("All price - %.2f", this.allPeople));
        return null;
    }
}
