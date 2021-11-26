package WorkingwithAbstraction.Lab.StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        String command = scanner.nextLine();
        while (!command.equals("Exit")) {
            String[] input = command.split(" ");
            switch (input[0]) {
                case "Create":
                    String name = input[1];
                    int age = Integer.parseInt(input[2]);
                    double grade = Double.parseDouble(input[3]);
                    Student student = new Student(name, age, grade);
                    studentSystem.add(student);
                    break;
                case "Show":
                    name = input[1];
                    String current = studentSystem.showStudent(name);
                    System.out.println(current);
                    break;
            }
            command = scanner.nextLine();
        }
    }
}
