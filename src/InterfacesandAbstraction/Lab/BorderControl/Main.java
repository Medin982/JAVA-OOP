package InterfacesandAbstraction.Lab.BorderControl;

import com.sun.source.doctree.BlockTagTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Identifiable> members = new ArrayList<>();
        String command = scan.nextLine();
        while (!command.equals("End")) {
            String[] arr = command.split("\\s+");
            if (arr.length == 2) {
                String model = arr[0];
                String id = arr[1];
                Robot robot = new Robot(model, id);
                members.add(robot);
            } else {
                String name = arr[0];
                int age = Integer.parseInt(arr[1]);
                String id = arr[2];
                Citizen citizen = new Citizen(name, age, id);
                members.add(citizen);
            }
            command = scan.nextLine();
        }
        String lastDigitsOfFakeIds = scan.nextLine();
        for (Identifiable member : members) {
            if (member.getId().endsWith(lastDigitsOfFakeIds)) {
                System.out.println(member.getId());
            }
        }
    }
}
