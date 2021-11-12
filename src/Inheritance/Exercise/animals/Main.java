package Inheritance.Exercise.animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String commnad = scan.nextLine();
        while (!commnad.equals("Beast!")) {
            String[] animalInformatiom = scan.nextLine().split("\\s+");
            String name = animalInformatiom[0];
            int age = Integer.parseInt(animalInformatiom[1]);
            String gender = animalInformatiom[2];
            Animal animal = null;
            try {
                switch (commnad) {
                    case "Cat":
                        animal = new Cat(name, age, gender);
                        break;
                    case "Dog":
                        animal = new Dog(name, age, gender);
                        break;
                    case "Frog":
                        animal = new Frog(name, age, gender);
                        break;
                    case "Kitten":
                        animal = new Kitten(name, age, gender);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(name, age, gender);
                        break;
                }
                System.out.println(animal);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            commnad = scan.nextLine();
        }
    }
}
