package UnitTesting.Exercise.p01_Database;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private static final Integer[] ARRAYS = {1, 2, 3};
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(ARRAYS);
    }

    @Test
    public void TestConstructorCreateValidDatabase() throws OperationNotSupportedException {
        database = new Database(ARRAYS);
        Integer[] dbNumbers = database.getElements();
        Assert.assertArrayEquals("Wrong count of db elements", dbNumbers, ARRAYS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void TestConstructorThrowsExceptionForMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[20];
         new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void TestConstructorThrowsExceptionForLessThan1Elements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test
    public void testDatabaseAddElementsCorectly() throws OperationNotSupportedException {
        database.add(2);
        Integer[] dbElements = database.getElements();
        Assert.assertEquals(database.getElements().length, ARRAYS.length + 1);
        Assert.assertEquals(Integer.valueOf(2), dbElements[dbElements.length -1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseAddMethodsThrowException() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testDatabaseRemoveOperationShouldRemovingCorrentElements() throws OperationNotSupportedException {
        int currentSizeArray = this.database.getElements().length;
        this.database.remove();
        Assert.assertEquals(currentSizeArray - 1, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseOperationThrowsExaption() throws OperationNotSupportedException {
        Integer[] arr = new Integer[0];
        Database db = new Database(arr);
        db.remove();
    }
}