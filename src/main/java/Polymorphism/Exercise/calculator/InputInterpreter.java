package Polymorphism.Exercise.calculator;

import java.util.Stack;

public class InputInterpreter {
    private  Stack<Integer> memory;
    private CalculationEngine engine;

    public InputInterpreter(CalculationEngine engine, Stack<Integer> stack){
        this.engine = engine;
        this.memory = stack;
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input));
        }catch (Exception ex){
            engine.pushOperation(this.getOperation(input));
        }
        return true;
    }
    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new MultiplicationOperation();
        } else if (operation.equals("/")) {
            return new Division();
        } else if (operation.equals("ms")) {
            return new MemorySafe(this.memory);
        } else if (operation.equals("mr")) {
           return new MemoryRecall(this.memory);
        }

        return null;
    }
}
