package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class HeapSortEfficiencyTest {

    private Sorting sortingMethod;
    private final int lenght = 1_000_000;
    private final long SEED = 1410;

    @Before
    public void setUp() {
        sortingMethod = new HeapSort();
    }

    @Test
    public void optimisticCase() {
        // given
        double[] nums = new double[lenght];
        double[] expected = new double[lenght];
        // when
        Arrays.fill(nums, 1.0);
        Arrays.fill(expected, 1.0);
        sortingMethod.sort(nums);
        // then
        Arrays.sort(expected);
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void pessimisticCase() {
        // given
        Random randomNums = new Random(SEED);
        Random randomExpected = new Random(SEED);
        double[] nums = randomNums.doubles(lenght).toArray();
        double[] expected = randomExpected.doubles(lenght).toArray();
        // when
        sortingMethod.sort(nums);
        // then
        Arrays.sort(expected);
        assertArrayEquals(expected, nums, 0);
    }

    @Test
    public void averageCase() {
        // given
        Random randomNums = new Random(SEED);
        Random randomExpected = new Random(SEED);
        double[] nums = randomNums.doubles(lenght).toArray();
        double[] expected = randomExpected.doubles(lenght).toArray();
        // when
        sortingMethod.sort(nums);
        // then
        Arrays.sort(expected);
        assertArrayEquals(expected, nums, 0);
    }



}

