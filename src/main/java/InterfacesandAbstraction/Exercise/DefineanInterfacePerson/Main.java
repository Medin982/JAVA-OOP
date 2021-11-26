package InterfacesandAbstraction.Exercise.DefineanInterfacePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Birthable> haveBirthDate = new ArrayList<>();
        String line = scan.nextLine();
        while (!line.equals("End")) {
            String[] arr = line.split("\\s+");
            switch (arr[0]) {
                case "Citizen":
                    String name = arr[1];
                    int age = Integer.parseInt(arr[2]);
                    String id = arr[3];
                    String birthDate = arr[4];
                    Citizen citizen = new Citizen(name, age, id, birthDate);
                    haveBirthDate.add(citizen);
                    break;
                case "Pet":
                    name = arr[1];
                    birthDate = arr[2];
                    Pet pet = new Pet(name, birthDate);
                    haveBirthDate.add(pet);
                    break;
                case "Robot":
                    String model = arr[1];
                    id = arr[2];
                    Robot robot = new Robot(id, model);
                    break;
            }
            line = scan.nextLine();
        }
        String year = scan.nextLine();
        for (Birthable birthable : haveBirthDate) {
            if (birthable.getBirthDate().endsWith(year)) {
                System.out.println(birthable.getBirthDate());
            }
        }
    }
}
