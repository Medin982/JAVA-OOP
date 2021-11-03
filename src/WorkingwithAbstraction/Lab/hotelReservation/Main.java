package WorkingwithAbstraction.Lab.hotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] information = scan.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(information[0]);
        int daysCount = Integer.parseInt(information[1]);
        String season = information[2];
        String discountType = information[3];
        PriceCalculator calculator = new PriceCalculator(pricePerDay, daysCount, Season.fromString(season), Discount.fromString(discountType));
        System.out.printf("%.2f", calculator.calculate());
    }
}
