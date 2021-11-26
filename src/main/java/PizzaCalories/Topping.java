package PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculataCalories() {
        double toppingModifiersValue = getToppingModifiersValue();
        return (2 * this.weight) * toppingModifiersValue;
    }

    private double getToppingModifiersValue() {
        if (this.toppingType.equals("Meat")) {
            return 1.2;
        } else if (this.toppingType.equals("Veggies")) {
            return 0.8;
        } else if (this.toppingType.equals("Cheese")) {
            return 1.1;
        }
        return 0.9;
    }

    private void setToppingType(String toppingType) {
        switch (toppingType) {
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                String messega = String.format("Cannot place %s on top of your pizza.",toppingType);
                throw new IllegalArgumentException(messega);
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            String message = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw new IllegalArgumentException(message);
        }
        this.weight = weight;
    }
}
