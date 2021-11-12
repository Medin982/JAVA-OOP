//package WorkingwithAbstraction.Exercise.JediGalaxy.greedyTimes;
//
//import java.util.LinkedHashMap;
//import java.util.Scanner;
//
//public class Encapsulation.Exercise.shoppingSpree.Main {
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        int capacity = Integer.parseInt(scanner.nextLine());
//        String[] safeItems = scanner.nextLine().split("\\s+");
//        int gold = 0;
//        int gems = 0;
//        int cash = 0;
//        Bags bags = new Bags(capacity);
//        Safe safe = new Safe(safeItems);
//        for (int i = 0; i < safeItems.length; i += 2) {
//            String name = safeItems[i];
//            int quantity = Integer.parseInt(safeItems[i + 1]);
//
//            String currentItem = "";
//
//            if (name.length() == 3) {
//                currentItem = "Cash";
//            } else if (name.toLowerCase().endsWith("gem")) {
//                currentItem = "Gem";
//            } else if (name.toLowerCase().equals("gold")) {
//                currentItem = "Gold";
//            }
//
//            if (!currentItem.equals("")) {
//                if (bags.bagsCapacity(quantity)) {
//                    continue;
//                }
//                switch (currentItem) {
//                    case "Gem":
//                        if (!bags.containsKey(currentItem)) {
//                            if (bags.containsKey("Gold")) {
//                                if (quantity > bags.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//                                    continue;
//                                }
//                            } else {
//                                continue;
//                            }
//                        } else if (bags.get(currentItem).values().stream().mapToLong(e -> e).sum() + quantity > bags.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//                            continue;
//                        }
//                        break;
//                    case "Cash":
//                        if (!bags.containsKey(currentItem)) {
//                            if (bags.containsKey("Gem")) {
//                                if (quantity > bags.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//                                    continue;
//                                }
//                            } else {
//                                continue;
//                            }
//                        } else if (bags.get(currentItem).values().stream().mapToLong(e -> e).sum() + quantity > bags.get("Gem").values().stream().mapToLong(e -> e).sum()) {
//                            continue;
//                        }
//                        break;
//                }
//            }
//
//            if (!bags.containsKey(currentItem)) {
//                bags.put((currentItem),new LinkedHashMap<String, Integer>());
//            }
//
//            if (!bags.get(currentItem).containsKey(name)) {
//                bags.get(currentItem).put(name, 0);
//            }
//
//            bags.get(currentItem).put(name, bags.get(currentItem).get(name) + quantity);
//            if (currentItem.equals("Gold")) {
//                gold += quantity;
//            } else if (currentItem.equals("Gem")) {
//                gems += quantity;
//            } else if (currentItem.equals("Cash")) {
//                cash += quantity;
//            }
//        }
//
//        for (var x : bags.entrySet()) {
//            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();
//
//            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));
//
//            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
//
//        }
//    }
//}