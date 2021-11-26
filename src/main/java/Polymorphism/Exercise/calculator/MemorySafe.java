package Polymorphism.Exercise.calculator;

import java.util.Stack;

public class MemorySafe implements Operation {
    private Stack<Integer> memory;

    public MemorySafe(Stack<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.push(operand);
    }

    @Override
    public int getResult() {
        return this.memory.peek();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
