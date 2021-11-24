package Reflection.Exercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scan = new Scanner(System.in);
        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();
        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);
        String line = scan.nextLine();
        while (!line.equals("END")) {
            String[] arrCommand = line.split("_");
            String commandName = arrCommand[0];
            int value = Integer.parseInt(arrCommand[1]);
            Method method = clazz.getDeclaredMethod(commandName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);
            System.out.println(innerValue.get(blackBoxInt));
            line = scan.nextLine();
        }
    }
}
