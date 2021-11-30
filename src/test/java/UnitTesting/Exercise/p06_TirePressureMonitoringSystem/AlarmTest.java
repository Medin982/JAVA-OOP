package UnitTesting.Exercise.p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTest {
    private static final double LOW_PRESSURE_VALUE = 10;
    private static final double HIGH_PRESSURE_VALUE = 23;
    private static final double NORMAL_PRESSURE_VALUE = 19;

    private Alarm alarm;


    @Test
    public void testAlarmSensorTurnedOnForLowPressureValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_VALUE);
        alarm = new Alarm(fakeSensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmSensorTurnedOnForHighPressureValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_VALUE);
        alarm = new Alarm(fakeSensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmSensorStayOffForNormalPressureValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE_VALUE);
        alarm = new Alarm(fakeSensor);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
}