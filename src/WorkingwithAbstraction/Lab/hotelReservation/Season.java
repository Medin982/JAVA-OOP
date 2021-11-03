package WorkingwithAbstraction.Lab.hotelReservation;

import java.util.Locale;

public enum Season {
    AUTUMN(1),
    SPRING(2),
    WINTER(3),
    SUMMER(4);
    private int multiplier;

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static Season fromString(String season) {
        return Season.valueOf(season.toUpperCase());
    }
}
