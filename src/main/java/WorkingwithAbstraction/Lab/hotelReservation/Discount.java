package WorkingwithAbstraction.Lab.hotelReservation;

public enum Discount {
    VIP(0.2),
    SECOND_VISIT(0.1),
    NONE(0.0);
    private double percentage;

    Discount(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public static Discount fromString(String discount) {
        Discount current = null;
        switch (discount) {
            case "VIP":
                current = VIP;
                break;
            case "SecondVisit":
                current = SECOND_VISIT;
                break;
            case "None":
                current = NONE;
                break;
            default:
                throw new IllegalArgumentException("Unknown discount type" + discount);
        }
        return current;
    }
}
