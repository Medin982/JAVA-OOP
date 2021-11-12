package Encapsulation.Lab;

import Encapsulation.Lab.FirstAndReserveTeam.Team;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Person person = null;
        Team team = new Team("Black Eagles");
        for (int i = 0; i < n; i++) {
            String[] input = scan.nextLine().split("\\s+");
            String firstName = input[0];
            String lastName = input[1];
            int age = Integer.parseInt(input[2]);
            double salary = Double.parseDouble(input[3]);
                 person = new Person(firstName, lastName, age, salary);
                 team.addPlayer(person);
        }
        System.out.printf("First team have %d players%n" +
                "Reserve team have %d players", team.getFirstTeam().size(), team.getReserveTeam().size());
    }

}
