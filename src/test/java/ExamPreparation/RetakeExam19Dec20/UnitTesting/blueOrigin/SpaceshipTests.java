package ExamPreparation.RetakeExam19Dec20.UnitTesting.blueOrigin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    Spaceship spaceship;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("SpaceShip", 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowsExceptionForNullOrWhiteSpace() {
        spaceship = new Spaceship(" ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowsExceptionIfCapacityIsLessThanZero() {
        spaceship = new Spaceship("Spaceship", - 1);
    }

    @Test
    public void testAddShouldAddedAstronautInSpaceship() {
        Astronaut astronaut = new Astronaut("Tommy", 80);
        spaceship.add(astronaut);
        assertEquals(1, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowsExceptionIfNoMoreCapacity() {
        Spaceship newSpaceShip = new Spaceship("Space", 1);
        Astronaut astronaut = new Astronaut("Tommy", 80);
        Astronaut astronaut2 = new Astronaut("JACk", 80);
        newSpaceShip.add(astronaut);
        newSpaceShip.add(astronaut2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowsExceptionIfAstronautExist() {
        Astronaut astronaut = new Astronaut("Tommy", 80);
        this.spaceship.add(astronaut);
        this.spaceship.add(astronaut);
    }

    @Test
    public void testRemoveShouldRemoveAstronautFromSpaceShip() {
        Astronaut astronaut = new Astronaut("Tommy", 80);
        this.spaceship.add(astronaut);
        assertTrue(this.spaceship.remove(astronaut.getName()));
        assertEquals(0, this.spaceship.getCount());
    }

    @Test
    public void testRemoveShouldReturnFalseIfNoSuchAstronaut() {
        Astronaut astronaut = new Astronaut("Tommy", 80);
        this.spaceship.add(astronaut);
        assertFalse(this.spaceship.remove("Jack"));
    }
}
