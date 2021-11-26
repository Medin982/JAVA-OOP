package WorkingwithAbstraction.Exercise.JediGalaxy.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimestions = getPosition(scanner.nextLine());
        int rows = dimestions[0];
        int cols = dimestions[1];
        Field field = new Field(rows, cols);
        Galaxy galaxy = new Galaxy(field);
        String command = scanner.nextLine();
        long starsCollected = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediPosition = getPosition(command);
            int[] evilPosition = getPosition(scanner.nextLine());
            int evilRows = evilPosition[0];
            int evilCols = evilPosition[1];
            galaxy.moveEvil(evilRows, evilCols);
            int jediRows = jediPosition[0];
            int jediCols = jediPosition[1];
            starsCollected = galaxy.moveJedi(jediRows, jediCols);
            command = scanner.nextLine();
        }
        System.out.println(starsCollected);
    }

    private static int[] getPosition(String command) {
        return Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
