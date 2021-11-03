//package WorkingwithAbstraction.Exercise.JediGalaxy.greedyTimes;
//
//import java.util.Collection;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Bags {
//    private int capacity;
//    private Map<String, Map<String, Integer>> date;
//
//    public Bags(int capacity) {
//        this.capacity = capacity;
//        this.date = new LinkedHashMap<>();
//    }
//
//    public boolean bagsCapacity(int quantity) {
//        return this.capacity < this.date.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + quantity;
//    }
//
//    public void
//}
