package ExamPreparation.Exams15August21.restaurant;

import ExamPreparation.Exams15August21.restaurant.core.ControllerImpl;
import ExamPreparation.Exams15August21.restaurant.core.EngineImpl;
import ExamPreparation.Exams15August21.restaurant.core.interfaces.Controller;
import ExamPreparation.Exams15August21.restaurant.entities.drinks.interfaces.Beverages;
import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import ExamPreparation.Exams15August21.restaurant.entities.tables.interfaces.Table;

import ExamPreparation.Exams15August21.restaurant.io.ConsoleReader;
import ExamPreparation.Exams15August21.restaurant.io.ConsoleWriter;
import ExamPreparation.Exams15August21.restaurant.repositories.BeverageRepositoryImpl;
import ExamPreparation.Exams15August21.restaurant.repositories.HealthFoodRepositoryImpl;
import ExamPreparation.Exams15August21.restaurant.repositories.TableRepositoryImpl;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.BeverageRepository;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.HealthFoodRepository;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.TableRepository;

public class Main {
    public static void main(String[] args) {
        // TODO: Optional - Create new instances for all repositories to test your code locally.

        HealthFoodRepository<HealthyFood> healthFoodRepository = new HealthFoodRepositoryImpl();
        BeverageRepository<Beverages> beverageRepository = new BeverageRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();


        Controller controller = new ControllerImpl(healthFoodRepository, beverageRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
