package pl.edu.pw.ee.EfficiencyTest;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.QuickSort;
import pl.edu.pw.ee.services.Sorting;

public class QuickSortEfficiencyTest {

    // Number of element:
    private final int lenght = 100;
    private final long SEED = 1410;

    private Sorting sortingMethod;
    private Random randomNums;
    private Random randomExpected;

    // optimisticCase
    private double[] O_nums;
    private double[] O_expected;

    // pessimisticCase
    private double[] P_nums;
    private double[] P_expected;

    // averageCase
    private double[] A_nums;
    private double[] A_expected;

    private void generateBestCase(double sortedNums[], int startIndex, int endIndex) {
        // swap middle element always to first position in each subset
        int size = endIndex - startIndex + 1;
        if (size > 1) {
            int mid = (size % 2) == 0 ? startIndex + (size / 2) : startIndex + ((size - 1) / 2);
            double tmp = sortedNums[mid];
            System.arraycopy(sortedNums, startIndex, sortedNums, startIndex + 1, mid - startIndex);
            sortedNums[startIndex] = tmp;
            generateBestCase(sortedNums, startIndex + 1, mid);
            generateBestCase(sortedNums, mid + 1, endIndex);
        }
    }

    @Before
    public void setUp() {
        sortingMethod = new QuickSort();

        // optimisticCase
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);
        O_nums = randomNums.doubles(lenght).toArray();
        O_expected = randomExpected.doubles(lenght).toArray();
        Arrays.sort(O_expected);
        Arrays.sort(O_nums);
        generateBestCase(O_nums, 0, O_nums.length - 1);

        // pessimisticCase
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);
        P_nums = randomNums.doubles(lenght).toArray();
        P_expected = randomExpected.doubles(lenght).toArray();
        Arrays.sort(P_nums);
        Arrays.sort(P_expected);

        // averageCase
        randomNums = new Random(SEED);
        randomExpected = new Random(SEED);
        A_nums = randomNums.doubles(lenght).toArray();
        A_expected = randomExpected.doubles(lenght).toArray();
        Arrays.sort(A_expected);
    }

    @Test
    public void optimisticCase() {
        // when
        sortingMethod.sort(O_nums);
        // then
        assertArrayEquals(O_expected, O_nums, 0);
    }

    @Test
    public void pessimisticCase() {
        // when
        sortingMethod.sort(P_nums);
        // then
        assertArrayEquals(P_expected, P_nums, 0);
    }

    @Test
    public void averageCase() {
        // when
        sortingMethod.sort(A_nums);
        // then
        assertArrayEquals(A_expected, A_nums, 0);
    }

}
