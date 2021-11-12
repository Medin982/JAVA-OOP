package PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Read pizza information
        Pizza pizza = null;
        String[] pizzaInformation = scan.nextLine().split("\\s+");
        String name = pizzaInformation[1];
        int count = Integer.parseInt(pizzaInformation[2]);
        try {
            pizza = new Pizza(name, count);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        // Read dough information
        String[] doughInfomation = scan.nextLine().split("\\s+");
        String flourType = doughInfomation[1];
        String bekingTechnique = doughInfomation[2];
        double weight = Double.parseDouble(doughInfomation[3]);
        try {
            Dough dough = new Dough(flourType, bekingTechnique, weight);
            pizza.setDough(dough);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        //Read toppping
        String command = scan.nextLine();
        while (!command.equals("END")) {
            String[] arr = command.split("\\s+");
            String toppingType = arr[1];
            double weightTopping = Double.parseDouble(arr[2]);
            try {
                Topping topping = new Topping(toppingType, weightTopping);
                pizza.addTopping(topping);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            command = scan.nextLine();
        }
        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }
}
