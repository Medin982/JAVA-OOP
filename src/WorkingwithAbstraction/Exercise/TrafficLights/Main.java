package WorkingwithAbstraction.Exercise.TrafficLights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Color[] signals = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Color::valueOf).toArray(Color[]::new);
        List<TrafficLight> trafficLightsList = new ArrayList<>();
        for (Color color : signals) {
            TrafficLight trafficLight = new TrafficLight(color);
            trafficLightsList.add(trafficLight);
        }
        int count = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < count; i++) {
            for (TrafficLight trafficLight : trafficLightsList) {
                trafficLight.changeColor();
                System.out.print(trafficLight + " ");
            }
            System.out.println();
        }
    }
}
