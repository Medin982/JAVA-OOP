package WorkingwithAbstraction.Exercise.CardRank;

public class Card {
    private CardRank cardRank;
    private SuitPowers suitPowers;

    public Card(CardRank cardRank, SuitPowers suitPowers) {
        this.cardRank = cardRank;
        this.suitPowers = suitPowers;
    }

    public int getPower() {
        return cardRank.getCardPower() + suitPowers.getSuitPower();
    }
}
