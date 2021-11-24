package Reflection.Exercise.barracksWars.core.factories;

import Reflection.Exercise.barracksWars.interfaces.Unit;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"Reflection.Exercise.barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		try {
			Class unitClazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor = unitClazz.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}
