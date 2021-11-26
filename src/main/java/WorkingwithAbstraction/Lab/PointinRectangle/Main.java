package WorkingwithAbstraction.Lab.PointinRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] coordinates = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        Point pointA = new Point(coordinates[0], coordinates[1]);
        Point pointC = new Point(coordinates[2], coordinates[3]);
        Rectangle rectangle = new Rectangle(pointA, pointC);
        int count = Integer.parseInt(scan.nextLine());
        while (count-- > 0) {
            int[] coordinatesOfPoints = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Point p = new Point(coordinatesOfPoints[0], coordinatesOfPoints[1]);
            boolean isInside = rectangle.isInside(p);
            System.out.println(isInside);
        }
    }
}
