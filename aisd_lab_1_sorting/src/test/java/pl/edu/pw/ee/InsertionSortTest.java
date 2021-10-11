package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class InsertionSortTest {

    private InsertionSort insertionSort;

    @Before
    public void setUp() {
        insertionSort = new InsertionSort();
    }

    @Test
    public void should_ReturnNothing() {
        // given
        double[] nums = { 4, 2, 3 };
        // when
        insertionSort.sort(nums);
        // then
        double[] expected = { 2, 3, 4 };
        assertEquals(expected, nums);
    }

}
