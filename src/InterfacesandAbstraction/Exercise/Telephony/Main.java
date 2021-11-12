package InterfacesandAbstraction.Exercise.Telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> numbersForCall = Arrays.stream(scan.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        List<String> urls = Arrays.stream(scan.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        Smartphone smartphone = new Smartphone(numbersForCall, urls);
        System.out.print(smartphone.call());
        System.out.print(smartphone.browse());
    }
}
