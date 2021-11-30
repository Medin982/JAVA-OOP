package UnitTesting.Exercise.p03_IteratorTest;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private static final String[] ELEMENTS = {"Bmw", "Mercedes", "Audi"};
    private ListIterator listIterator;


    @Test(expected = OperationNotSupportedException.class)
    public void TestConstructorShouldThrowExceptionForNull() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(null);
    }

    @Test
    public void testConstructorSuccesfullStoreElementsoInList() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
    }

    @Test
    public void testHasNextMethodsReturnedTrueIfHaveNextElement() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
        Assert.assertTrue(listIterator.hasNext());
    }

    @Test
    public void testHasNextMethodsReturnedFalseWhenInitialIndexIsOnLastElements() throws OperationNotSupportedException {
        listIterator = new ListIterator("Gosho");
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test
    public void testMoveMethodsReturnedTrueWhenSuccesfullMoveInitialIndex() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
    }

    @Test
    public void testMoveMethodsReturnedFalseWhenNoMoreElements() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintMethodsShouldThrowsExceptionAndPrintMessage() throws OperationNotSupportedException {
        listIterator = new ListIterator();
        listIterator.print();
    }

    @Test
    public void testPrintSuccessful() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
        int index = 0;
        while (listIterator.hasNext()) {
            Assert.assertEquals(ELEMENTS[index], listIterator.print());
            index++;
            listIterator.move();
        }
    }
}