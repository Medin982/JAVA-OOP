package Polymorphism.Exercise.WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        List<Animal> animalList = new ArrayList<>();
        while (!line.equals("End")) {
            String[] animalInformation = line.split("\\s+");
            Animal animal = createAnimal(animalInformation);
            animal.makeSound();
            String[] foodInformation = scan.nextLine().split("\\s+");
            Food food = createFood(foodInformation);
            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            animalList.add(animal);
            line = scan.nextLine();
        }
        animalList.forEach(System.out::println);
    }

    private static Food createFood(String[] foodInformation) {
        String foodType = foodInformation[0];
        Integer quantity = Integer.parseInt(foodInformation[1]);
        if (foodType.equals("Meat")) {
            return new Meat(quantity);
        } else return new Vegetable(quantity);
    }

    private static Animal createAnimal(String[] animalInformation) {
        String animalType = animalInformation[0];
        String animalName = animalInformation[1];
        Double animalWeight = Double.parseDouble(animalInformation[2]);
        String animalLivingRegion = animalInformation[3];
        switch (animalType) {
            case "Mouse":
                return new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
            case "Zebra":
                return new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
            case "Cat":
                String catBreed = animalInformation[4];
                return new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
            case "Tiger":
                return new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
            default:
                throw new IllegalArgumentException("No such animal");
        }
    }
}


