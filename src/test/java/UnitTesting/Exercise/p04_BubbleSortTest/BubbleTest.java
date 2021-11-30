package UnitTesting.Exercise.p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void testReplacingSmallestNumberFurtherInTheArray() {
        int[] elemennts = {8, 3, 5, 2, 9, 20};
        Bubble.sort(elemennts);
        int[] sortedArray = {2, 3, 5, 8, 9, 20};
        Assert.assertArrayEquals( "Bubble sort does now work by default!"
                ,sortedArray, elemennts);
    }

}