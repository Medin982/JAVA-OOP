package Inheritance.Exercise.NeedforSpeed;

public class Motorcycle extends Vehicle {

    public Motorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(getFuelConsumption());
    }
}
