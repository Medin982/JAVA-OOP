package WorkingwithAbstraction.Exercise.CardRank;

public enum SuitPowers {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);
    private int suitPower;

    SuitPowers(int power) {
        this.suitPower = power;
    }

    public int getSuitPower() {
        return suitPower;
    }
}
