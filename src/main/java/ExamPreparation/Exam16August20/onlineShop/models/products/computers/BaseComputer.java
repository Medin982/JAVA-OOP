package ExamPreparation.Exam16August20.onlineShop.models.products.computers;

import ExamPreparation.Exam16August20.onlineShop.models.products.BaseProduct;
import ExamPreparation.Exam16August20.onlineShop.models.products.components.Component;
import ExamPreparation.Exam16August20.onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static ExamPreparation.Exam16August20.onlineShop.common.constants.ExceptionMessages.*;
import static ExamPreparation.Exam16August20.onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()) {
            return super.getOverallPerformance();
        }

        double avgPerformance = this.components.stream()
                .mapToDouble(Component::getOverallPerformance)
                .average()
                .orElse(0);

        return super.getOverallPerformance() + avgPerformance;
    }

    @Override
    public double getPrice() {
        double componentsSum = this.components.stream()
                .mapToDouble(Component::getPrice)
                .sum();

        double peripheralsSum = this.peripherals.stream()
                .mapToDouble(Peripheral::getPrice)
                .sum();

        return super.getPrice() + componentsSum + peripheralsSum;
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (foundComponent(this.components, component)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    component.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component  removeComponent(String componentType) {
        Component component = this.components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);

        if (this.components.isEmpty() || component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, getClass().getSimpleName(), getId()));
        }

        this.components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (foundPeripheralByType(this.peripherals, peripheral)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
        }

        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals.stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        if (this.peripherals.isEmpty() || peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType, getClass().getSimpleName(), getId()));
        }

        this.peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(PRODUCT_TO_STRING, this.getOverallPerformance(), this.getPrice(),
                this.getClass().getSimpleName(), super.getManufacturer(), super.getModel(), super.getId()))
                .append(System.lineSeparator());
        builder.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, this.components.size()))
                .append(System.lineSeparator());
        this.components.forEach(component -> builder.append("  ").append(component.toString()).append(System.lineSeparator()));
        double avg = this.peripherals.stream()
                .mapToDouble(Peripheral::getOverallPerformance)
                .average()
                .orElse(0);
        builder.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(), avg))
                .append(System.lineSeparator());
        this.peripherals.forEach(peripheral -> builder.append("  ").append(peripheral.toString()).append(System.lineSeparator()));
        return builder.toString();
    }

    private boolean foundComponent(List<Component> components, Component component) {
        return  components.stream()
                .anyMatch(c -> c.getClass().getSimpleName().equals(component.getClass().getSimpleName())
                        || c.getId() == component.getId());
    }

    private boolean foundPeripheralByType(List<Peripheral> peripherals, Peripheral peripheral) {
        return  peripherals.stream()
                .anyMatch(p -> p.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())
                        || p.getId() == peripheral.getId());
    }
}
