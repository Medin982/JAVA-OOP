package WorkingwithAbstraction.Lab.Rhombus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Rhombus rhombus = new Rhombus(n);
        System.out.println(rhombus.getFigure());
    }
}
