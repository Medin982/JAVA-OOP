package SOLID;

import java.util.Scanner;

public class Input {

    public String readLoggerInfo(Scanner scan) {
        int n = Integer.parseInt(scan.nextLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            sb.append(scan.nextLine()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
