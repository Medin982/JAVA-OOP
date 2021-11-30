package UnitTesting.Exercise.p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


public class DatabaseTest {

    private Database database;
    private Person person;

    @Before
    public void setUp() throws OperationNotSupportedException {
        person = new Person(1234, "Ivan");
        database = new Database(new Person[10]);
    }

    @Test
    public void testConstructorCreateSuccessfullDatabase() throws OperationNotSupportedException {
        Person[] people = new Person[10];
        database = new Database(people);
        Assert.assertArrayEquals(database.getElements(), people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testContructorShouldThrowsExceptionForMoreThan16ElementsOrLessThan1() throws OperationNotSupportedException {
        Person[] people = new Person[18];
        new Database(people);
        Person[] people1 = new Person[0];
        new Database(people1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddMethodShouldThrowsExceptionIfThereAreNegativOrNullIds() throws OperationNotSupportedException {
        database.add(new Person(-123, "Ivan"));
        database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveMethodsShouldThrowsExceptionForArraysIndexOutForBoundsException() throws OperationNotSupportedException {
        while (true) {
            database.remove();
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMethodsShouldThrowExceptionIfNoUserIsPresentUsername() throws OperationNotSupportedException {
        String searchingUsers = "Gosho";
        database.findByUsername(searchingUsers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMethodsShouldThrowExceptionIfUsernameParameturIsNull() throws OperationNotSupportedException {
        String searchingUsers = null;
        database.findByUsername(searchingUsers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMethodsShouldThrowsExceptionIfSizeIsDifferetby1() throws OperationNotSupportedException {
        database.findByUsername(person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdMethodsShouldThrowsExceptionIfNoUserIsPresentId() throws OperationNotSupportedException {
        int id = 2355;
        database.findById(id);
    }
}
