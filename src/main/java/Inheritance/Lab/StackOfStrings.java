package Inheritance.Lab;

import java.util.ArrayList;

public class StackOfStrings {
    private ArrayList<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item) {
        this.data.add(item);
    }

    public String pop() {
        String current = this.data.get(data.size() - 1);
        this.data.remove(current);
        return current;
    }

    public String peek() {
        String current = this.data.get(data.size() - 1);
        return current;
    }

    public boolean isEmpty() {
        if (this.data.isEmpty()) {
            return true;
        }
        return false;
    }
}
