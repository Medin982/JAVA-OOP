package ExamPreparation.Exam16August20.UniteTesting.computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {

    private ComputerManager computerManager;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputerReturnUnmodifiablelIst() {
        computerManager = new ComputerManager();
        computerManager.getComputers().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldThrowsExceptionIfGivenComputerIsNull() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerShouldThrowsExceptionIfComputerExist() {
        Computer computer = new Computer("Intel", "i5", 20.99);
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAddComputerShouldAddedComputerCorrectly() {
        Computer computer = new Computer("Intel", "i5", 20.99);
        this.computerManager.addComputer(computer);
        Assert.assertEquals(1, this.computerManager.getCount());
    }

    @Test
    public void testRemoveComputerShouldRemovedComputerCorrectly() {
        Computer computer = new Computer("Intel", "i5", 20.99);
        this.computerManager.addComputer(computer);
        Computer actual = this.computerManager.removeComputer("Intel", "i5");
        Assert.assertEquals(0, this.computerManager.getCount());
        Assert.assertEquals(computer, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerShouldThrowsExceptionIfGivenManufacturerOrModelIsNull() {
        this.computerManager.getComputer("Intel", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerShouldThrowsExceptionIfNoSuchComputer() {
        this.computerManager.getComputer("Intel", "i5");
    }

    @Test
    public void testGetComputerShouldReturnComputerByGivenManufacturerAndModel() {
        Computer computer = new Computer("Intel", "i5", 20.99);
        this.computerManager.addComputer(computer);
        Computer actual = this.computerManager.getComputer("Intel", "i5");
        Assert.assertEquals(computer, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerByManufacturerShouldThrowsExceptionIfGivenManufacturerIsNull() {
        this.computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputerByManufacturerShouldReturnComputersByGivenManufacturer() {
        Computer computer = new Computer("Intel", "i5", 20.99);
        Computer computer2 = new Computer("Intel", "i7", 30.99);
        List<Computer> expected = new ArrayList<>();
        expected.add(computer);
        expected.add(computer2);
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer2);
        List<Computer> computersByManufacturer = this.computerManager.getComputersByManufacturer("Intel");
        Assert.assertEquals(expected, computersByManufacturer);
    }
}