package ExamPreparation.Exams15August21.restaurant.repositories;

import ExamPreparation.Exams15August21.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import ExamPreparation.Exams15August21.restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private Collection<HealthyFood> healthyFoods;

    public HealthFoodRepositoryImpl() {
        this.healthyFoods = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.healthyFoods.stream()
                .filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(healthyFoods);
    }

    @Override
    public void add(HealthyFood entity) {
        this.healthyFoods.add(entity);
    }
}
