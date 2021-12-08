package ExamPreparation.Exam10April21.aquarium;

import ExamPreparation.Exam10April21.UnitTesting.Aquarium;
import ExamPreparation.Exam10April21.UnitTesting.Fish;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AquariumTests {

    private Aquarium aquarium;

    @Before
    public void setUp() {
        this.aquarium = new Aquarium("Tommy", 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldReturnExceptionIfNameIsNullOrWhiteSpace() {
        new Aquarium(" ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowsExceptionIfCapacityIsLessThanZero() {
        new Aquarium("Tommy", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowsExceptionIfNoMoreCapacity() {
        Fish fish = new Fish("Sammy");
        Aquarium currentAquarium = new Aquarium("Benny", 1);
        currentAquarium.add(fish);
        currentAquarium.add(fish);
    }

    @Test
    public void testAddShouldAddedGivenFishInAquarium() {
        Fish fish = new Fish("Sammy");
        int expected = this.aquarium.getCount();
        this.aquarium.add(fish);
        int actual = this.aquarium.getCount();
        assertEquals(expected + 1, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowsExceptionIfNoSuchFishByGivenName() {
        this.aquarium.remove("Sammy");
    }

    @Test
    public void testRemoveShouldRemovedFishFromAquarium() {
        Fish fish = new Fish("Sammy");
        Fish secondFish = new Fish("Jack");
        this.aquarium.add(fish);
        this.aquarium.add(secondFish);
        int expected = this.aquarium.getCount();
        this.aquarium.remove("Jack");
        int actual = this.aquarium.getCount();
        assertEquals(expected - 1, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldThrowsExceptionIfNoSuchFishByGivenName() {
        this.aquarium.remove("Sammy");
    }

    @Test
    public void testSellFishReturnFishByGivenName() {
        Fish fish = new Fish("Sammy");
        Fish secondFish = new Fish("Jack");
        this.aquarium.add(fish);
        this.aquarium.add(secondFish);
        Fish actual = this.aquarium.sellFish("Sammy");
        assertFalse(actual.isAvailable());
        assertEquals(fish, actual);
    }

    @Test
    public void testReportShouldReturnCorrectlyInformation() {
        Fish fish = new Fish("Sammy");
        Fish secondFish = new Fish("Jack");
        this.aquarium.add(fish);
        this.aquarium.add(secondFish);
        String fishNames = "Sammy, Jack";
        String expected = String.format("Fish available at %s: %s", this.aquarium.getName(), fishNames);
        String actual = this.aquarium.report();
        assertEquals(expected, actual);
    }
}

