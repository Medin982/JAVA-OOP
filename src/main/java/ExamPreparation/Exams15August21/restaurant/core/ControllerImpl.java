package ExamPreparation.Exams15August21.restaurant.core;

import ExamPreparation.Exams15August21.restaurant.common.ExceptionMessages;
import ExamPreparation.Exams15August21.restaurant.common.OutputMessages;
import ExamPreparation.Exams15August21.restaurant.common.enums.BeveragesType;
import ExamPreparation.Exams15August21.restaurant.common.enums.HealthyFoodType;
import ExamPreparation.Exams15August21.restaurant.common.enums.TableType;
import ExamPreparation.Exams15August21.restaurant.core.interfaces.Controller;
import ExamPreparation.Exams15August21.restaurant.entities.drinks.impl.Fresh;
import ExamPreparation.Exams15August21.restaurant.entities.drinks.impl.Smoothie;
import ExamPreparation.Exams15August21.restaurant.entities.drinks.interfaces.Beverages;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.Impl.Salad;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.Impl.VeganBiscuits;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import ExamPreparation.Exams15August21.restaurant.entities.tables.impl.InGarden;
import ExamPreparation.Exams15August21.restaurant.entities.tables.impl.Indoors;
import ExamPreparation.Exams15August21.restaurant.entities.tables.interfaces.Table;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.BeverageRepository;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.HealthFoodRepository;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.TableRepository;


public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;

    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food;
        if (HealthyFoodType.Salad.name().equals(type)) {
            food = new Salad(name, price);
            return addedToFoodRepository(name, food);
        } else if (HealthyFoodType.VeganBiscuits.name().equals(type)) {
            food = new VeganBiscuits(name, price);
            return addedToFoodRepository(name, food);
        }
        return null;
    }

    private String addedToFoodRepository(String name, HealthyFood food) {
        if (this.healthFoodRepository.foodByName(name) == null) {
            this.healthFoodRepository.add(food);
            return String.format(OutputMessages.FOOD_ADDED, name);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages;
        if (BeveragesType.Fresh.name().equals(type)) {
            beverages = new Fresh(name, counter, brand);
            return addedToBeverageRepository(name, beverages);
        } else if (BeveragesType.Smoothie.name().equals(type)) {
            beverages = new Smoothie(name, counter, brand);
            return addedToBeverageRepository(name, beverages);
        }
        return null;
    }

    private String addedToBeverageRepository(String name, Beverages beverages) {
        if (this.beverageRepository.beverageByName(name, beverages.getBrand()) == null) {
            this.beverageRepository.add(beverages);
            return String.format(OutputMessages.BEVERAGE_ADDED,
                    beverages.getClass().getSimpleName(), beverages.getBrand());
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        if (TableType.Indoors.name().equals(type)) {
            table = new Indoors(tableNumber, capacity);
            return addedToTableRepository(tableNumber, table);
        } else if (TableType.InGarden.name().equals(type)) {
            table = new InGarden(tableNumber, capacity);
            return addedToTableRepository(tableNumber, table);
        }
        return null;
    }

    private String addedToTableRepository(int tableNumber, Table table) {
        if (this.tableRepository.byNumber(tableNumber) == null) {
            this.tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED, tableNumber);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table tableToreserve = this.tableRepository.getAllEntities().stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);
        if (tableToreserve == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        tableToreserve.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, tableToreserve.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        if (this.tableRepository.byNumber(tableNumber) == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        if (this.healthFoodRepository.foodByName(healthyFoodName) == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        Table table = tableRepository.byNumber(tableNumber);
        table.orderHealthy(this.healthFoodRepository.foodByName(healthyFoodName));
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        if (this.tableRepository.byNumber(tableNumber) == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        if (this.beverageRepository.beverageByName(name, brand) == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }
        Table table = this.tableRepository.byNumber(tableNumber);
        table.orderBeverages(this.beverageRepository.beverageByName(name, brand));
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        this.totalMoney += bill;
        table.clear();
        return String.format(OutputMessages.BILL, tableNumber, bill);

    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY, this.totalMoney);
    }
}
