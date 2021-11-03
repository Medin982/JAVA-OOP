package WorkingwithAbstraction.Lab.hotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int days;
    private Season season;
    private Discount discount;

    public PriceCalculator(double pricePerDay, int days, Season season, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.season = season;
        this.discount = discount;
    }

    public double calculate() {
        int multiplier = this.season.getMultiplier();
        double pecentDiscount = this.discount.getPercentage();
        return this.pricePerDay * this.days * multiplier * (1 - pecentDiscount);
    }
}
