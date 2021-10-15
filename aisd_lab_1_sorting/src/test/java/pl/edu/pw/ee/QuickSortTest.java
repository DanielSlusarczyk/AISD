package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

    private QuickSort quickSort;

    @Before
    public void setUp() {
        quickSort = new QuickSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ArrayIsNull() {
        // given
        double[] nums = null;
        // when
        quickSort.sort(nums);
    }

    @Test
    public void should_ReturnArray_When_ArrayIsEmpty() {
        // given
        double[] nums = {};
        // when
        quickSort.sort(nums);
        // then
        double[] expected = {};
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnArray_When_ArrayHasOneElem() {
        // given
        double[] nums = { 1 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnArray_When_ArrayHasTwoSortedElem() {
        // given
        double[] nums = { 1, 2 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1, 2 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasTwoUnsortedElem() {
        // given
        double[] nums = { 2, 1 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1, 2 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnArray_When_ArrayHasTwoEqualElem() {
        // given
        double[] nums = { 1, 1 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1, 1 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasUnsortedElem() {
        // given
        double[] nums = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasRandomElem() {
        // given
        double[] nums = { 7, 3, 1, 8, 4, 2, 6, 3, 5, 9, 0 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasNegativedElem() {
        // given
        double[] nums = { -3, -4, -2, -4 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { -4, -4, -3, -2 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnArray_When_OptimisticArray() {
        // given
        double[] nums = { 5, 1, 2, 3, 4, 6, 7, 8, 9 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_PessimisticArray() {
        // given
        double[] nums = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        // when
        quickSort.sort(nums);
        // then
        double[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void should_ReturnSortedArray_When_ArrayHasManyElem() {
        // given
        final long SEED = 1410;
        final int lenght = 100_000;

        double[] nums = new double[lenght];
        Random random = new Random(SEED);
        for (double x : nums) {
            x = random.nextDouble();
        }
        // when
        for (double x : nums) {
            System.out.print(x);
        }
        quickSort.sort(nums);
        // then
        double[] expected = new double[lenght];
        for (double x : expected) {
            x = random.nextDouble();
        }
        Arrays.sort(expected);

        assertArrayEquals(expected, nums, 0);
    }

}
