package Polymorphism.Exercise.Vehicles;

public abstract class Vehicles {
    private double fuelQuantity;
    private double  fuelConsumption;
    private double capacity;

    public Vehicles(double fuelQuantity, double fuelConsumption, double capacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    abstract String drive(double distance);
    abstract void refueled(double liters);
}
