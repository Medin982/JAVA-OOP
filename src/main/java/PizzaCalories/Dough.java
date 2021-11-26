package PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        double modifiersTypeValue = getModifiersTypeValue();
        double bekingModifiersValue = getBekingModifiersValue();
        return (2 * this.weight) * modifiersTypeValue * bekingModifiersValue;
    }

    private double getBekingModifiersValue() {
        if (this.bakingTechnique.equals("Crispy")) {
            return 0.9;
        } else if (this.bakingTechnique.equals("Chewy")) {
            return 1.1;
        }
        return 1.0;
    }

    private double getModifiersTypeValue() {
        if (this.flourType.equals("White")) {
            return 1.5;
        }
        return 1.0;
    }

    private void setFlourType(String flourType) {
        switch (flourType) {
            case "White":
            case "Wholegrain":
                this.flourType = flourType;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
}
