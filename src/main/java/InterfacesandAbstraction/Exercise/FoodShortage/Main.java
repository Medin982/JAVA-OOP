package InterfacesandAbstraction.Exercise.FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(scan.nextLine());
        List<Citizen> citizens = new ArrayList<>();
        List<Rebel> rebels = new ArrayList<>();
        while (numberOfPeople-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");
            if (tokens.length == 4) {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                String birthDate = tokens[3];
                Citizen citizen = new Citizen(name, age, id, birthDate);
                citizens.add(citizen);
            } else {
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String group = tokens[2];
                Rebel rebel = new Rebel(name, age, group);
                rebels.add(rebel);
            }
        }
        String buyers = scan.nextLine();
        int sum = 0;
        while (!buyers.equals("End")) {
             sum += buyerIsExist(buyers, citizens, rebels);
            buyers = scan.nextLine();
        }
        System.out.println(sum);
    }

    private static int buyerIsExist(String buyers, List<Citizen> citizens, List<Rebel> rebels) {
        int sum = 0;
        for (Citizen citizen : citizens) {
            if (citizen.getName().equals(buyers)) {
                citizen.buyFood();
                sum = 10;
            }
        }
        for (Rebel rebel : rebels) {
            if (rebel.getName().equals(buyers)) {
                rebel.buyFood();
                sum = 5;
            }
        }
        return sum;
    }
}
