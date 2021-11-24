package Reflection.Exercise.barracksWars;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.Runnable;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import Reflection.Exercise.barracksWars.core.Engine;
import Reflection.Exercise.barracksWars.core.factories.UnitFactoryImpl;
import Reflection.Exercise.barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
