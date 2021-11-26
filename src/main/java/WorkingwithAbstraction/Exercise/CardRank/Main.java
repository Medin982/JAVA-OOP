package WorkingwithAbstraction.Exercise.CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CardRank cardRank = CardRank.valueOf(scan.nextLine());
        SuitPowers suitPower = SuitPowers.valueOf(scan.nextLine());
        Card card = new Card(cardRank, suitPower);
        System.out.printf("Card name: %s of %s; Card power: %d", cardRank.name(), suitPower.name(),
                card.getPower());
    }
}
