package UnitTesting.Exercise.p05_CustomLinkedList;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CustomLinkedListTest {

    private CustomLinkedList<Integer> customLinkedList;

    @Before
    public void setUp() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(3);
        customLinkedList.add(4);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMethodsShouldThrowsExceptionForInvalidIndex() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.get(-1);
    }

    @Test
    public void testAddMethodsCorrectAddElementsAtTheEndOfTheLIst() {
        int count = customLinkedList.getCount();
        customLinkedList.add(3);
        Assert.assertEquals(count + 1, customLinkedList.getCount());
        Integer integer = customLinkedList.get(customLinkedList.getCount() - 1);
        Assert.assertEquals(Integer.valueOf(3), integer);
    }

    @Test
    public void testGetMethodsCorrectReturnedElements() {
        Integer integer = customLinkedList.get(1);
        Assert.assertEquals("Invalid index", Integer.valueOf(4), integer);
    }

    @Test
    public void testGetCountsMethodsShouldReturnedSizeOfTheLIst() {
        int count = customLinkedList.getCount();
        Assert.assertEquals("getCount methods returned wrong result", 2, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMethodsShouldThrowsExceptionForInvalidIndex() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.set(-1, 2);
    }

    @Test
    public void testSetMethodsCorrectSetElementsOnGivenIndex() {
        customLinkedList.set(1, 1);
        Assert.assertEquals(Integer.valueOf(1), customLinkedList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtMethodsShouldThrowsExceptionForInvalidIndex() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.removeAt(-1);
    }

    @Test
    public void testRemoveAtMethodsCorrectRemoveElementsOnGivenIndex() {
        customLinkedList.add(4);
        int count = customLinkedList.getCount();
        Integer integer = customLinkedList.get(2);
        customLinkedList.removeAt(1);
        Assert.assertEquals(count - 1, customLinkedList.getCount());
        Assert.assertEquals(integer, customLinkedList.get(1));
    }

    @Test
    public void testRemoveMethodsShouldReturnedTheIndexOfTheRemovedElements() {
        int count = customLinkedList.getCount();
        int removedIndex = customLinkedList.remove(3);
        //Check if the returned index is same
        Assert.assertEquals( 0, removedIndex);
        //Check if the size is reduced
        Assert.assertEquals(count - 1, customLinkedList.getCount());
    }

    @Test
    public void testRemoveMethodsShouldReturnedNegativeIndexIfNotFoundElements() {
        int remove = customLinkedList.remove(5);
        Assert.assertEquals("Not found element", -1, remove);
    }

    @Test
    public void testIndexOfMethodsShouldReturnedIndexOfTheFirstOccurrenceOfTheElements() {
        customLinkedList.add(4);
        int index = customLinkedList.indexOf(4);
        Assert.assertEquals(1, index);
    }

    @Test
    public void testIndexOfMethodsShouldReturnedNegativeIndexIfNotFoundElements() {
        int remove = customLinkedList.indexOf(5);
        Assert.assertEquals("Not found element", -1, remove);
    }
}