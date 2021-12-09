package ExamPreparation.Exam16August20.onlineShop.core;

import ExamPreparation.Exam16August20.onlineShop.core.interfaces.Controller;
import ExamPreparation.Exam16August20.onlineShop.models.products.components.*;
import ExamPreparation.Exam16August20.onlineShop.models.products.computers.Computer;
import ExamPreparation.Exam16August20.onlineShop.models.products.computers.DesktopComputer;
import ExamPreparation.Exam16August20.onlineShop.models.products.computers.Laptop;
import ExamPreparation.Exam16August20.onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static ExamPreparation.Exam16August20.onlineShop.common.constants.ExceptionMessages.*;
import static ExamPreparation.Exam16August20.onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Collection<Computer> computers;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        if (this.computers.stream().anyMatch(c -> c.getId() == id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        this.computers.add(computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer,
                                String model, double price, double overallPerformance, String connectionType) {
        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        Computer computer = returnComputerByGivenId(computerId);

        try {
            computer.addPeripheral(peripheral);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }

        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = returnComputerByGivenId(computerId);
        Peripheral peripheral;
        try {
            peripheral = computer.removePeripheral(peripheralType);
        } catch (IllegalArgumentException e) {
          return e.getMessage();
        }
        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer,
                               String model, double price, double overallPerformance, int generation) {
        Component component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        Computer computer = returnComputerByGivenId(computerId);

        try {
            computer.addComponent(component);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = returnComputerByGivenId(computerId);
        Component component;
        try {
            component = computer.removeComponent(componentType);
        } catch (IllegalArgumentException e) {
           return e.getMessage();
        }

        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = returnComputerByGivenId(id);
        this.computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer computer = this.computers.stream()
                .filter(p -> p.getPrice() <= budget)
                .max(Comparator.comparing(Computer::getOverallPerformance))
                .orElse(null);
        if (computer == null || this.computers.isEmpty()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
        this.computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = returnComputerByGivenId(id);
        return computer.toString();
    }

    private Computer returnComputerByGivenId(int id) {
        Computer computer = this.computers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer;
    }
}
