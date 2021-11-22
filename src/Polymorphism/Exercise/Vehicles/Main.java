package Polymorphism.Exercise.Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Car car = getCar(scan);
        Truck truck = getTruck(scan);
        Bus bus = getBus(scan);
        int n = Integer.parseInt(scan.nextLine());
        while (n-- > 0) {
            String[] command = scan.nextLine().split("\\s+");
            switch (command[0]) {
                case "Drive":
                    if (command[1].equals("Car")) {
                        System.out.println(car.drive(Double.parseDouble(command[2])));
                    } else if (command[1].equals("Truck")) {
                        System.out.println(truck.drive(Double.parseDouble(command[2])));
                    } else {
                        try {
                            System.out.println(bus.drive(Double.parseDouble(command[2])));
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "Refuel":
                    try {
                        if (command[1].equals("Car")) {
                            car.refueled(Double.parseDouble(command[2]));
                        } else if (command[1].equals("Truck")) {
                            truck.refueled(Double.parseDouble(command[2]));
                        } else {
                            bus.refueled(Double.parseDouble(command[2]));
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "DriveEmpty":
                    try {
                        System.out.println(bus.driveEmpty(Double.parseDouble(command[2])));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f", bus.getFuelQuantity());
    }

    private static Car getCar(Scanner scan) {
        String[] carInformantion = scan.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(carInformantion[1]);
        double fuelConsumption = Double.parseDouble(carInformantion[2]);
        double capacity = Double.parseDouble(carInformantion[3]);
        Car car = new Car(fuelQuantity, fuelConsumption, capacity);
        return car;
    }

    private static Truck getTruck(Scanner scan) {
        String[] carInformantion = scan.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(carInformantion[1]);
        double fuelConsumption = Double.parseDouble(carInformantion[2]);
        double capacity = Double.parseDouble(carInformantion[3]);
        Truck truck = new Truck(fuelQuantity, fuelConsumption, capacity);
        return truck;
    }

    private static Bus getBus(Scanner scan) {
        String[] carInformantion = scan.nextLine().split("\\s+");
        double fuelQuantity = Double.parseDouble(carInformantion[1]);
        double fuelConsumption = Double.parseDouble(carInformantion[2]);
        double capacity = Double.parseDouble(carInformantion[3]);
        Bus bus = new Bus(fuelQuantity, fuelConsumption, capacity);
        return bus;
    }
}
