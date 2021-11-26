package Encapsulation.Exercise.shoppingSpree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new HashMap<>();

        //read People information
        String[] peopleDate = scan.nextLine().split(";");
        for (String currentPerson : peopleDate) {
            String[] personDate = currentPerson.split("=");
            String name = personDate[0];
            double money = Double.parseDouble(personDate[1]);
            try {
                Person person = new Person(name, money);
                people.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        //read Encapsulation.Exercise.shoppingSpree.Product information
        String[] productsDate = scan.nextLine().split(";");
        for (String currentProduct : productsDate) {
            String[] currentProductDate = currentProduct.split("=");
            String nameProduct = currentProductDate[0];
            double cost = Double.parseDouble(currentProductDate[1]);
            try {
                Product product = new Product(nameProduct, cost);
                products.put(nameProduct, product);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        //shopping
        String command = scan.nextLine();
        while (!command.equals("END")) {
            String[] arr = command.split("\\s+");
            String personName = arr[0];
            String productName = arr[1];
            try {
                Person person = people.get(personName);
                Product product = products.get(productName);
                person.buyProduct(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = scan.nextLine();
        }

        //print result
        for (Person person : people.values()) {
            System.out.print(person.getName() + " - ");
            if (person.getProducts().isEmpty()) {
                System.out.println("Nothing bought");
            } else {
                System.out.println(person.getProducts().stream().map(Product::getName).collect(Collectors.joining(", ")));
            }
        }
    }
}
