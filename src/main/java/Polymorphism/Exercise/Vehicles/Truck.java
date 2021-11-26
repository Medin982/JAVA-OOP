package Polymorphism.Exercise.Vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicles {
    private static final double AC_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double capacity) {
        super(fuelQuantity, fuelConsumption, capacity);
    }

    @Override
    String drive(double distance) {
        DecimalFormat format = new DecimalFormat("##.##");
        double neededFuel = distance * (this.getFuelConsumption() + AC_CONSUMPTION);
        if (neededFuel < this.getFuelQuantity()) {
            this.setFuelQuantity(this.getFuelQuantity() - neededFuel);
            return String.format("Truck travelled %s km", format.format(distance));
        }
        return String.format("Truck needs refueling");
    }

    @Override
    void refueled(double liters) {
        if (this.getFuelQuantity() + liters > this.getCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.setFuelQuantity(this.getFuelQuantity() + (liters * 0.95));
    }

}
