package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

    private Searcher searcher;

    @Before
    public void setUp() {
        searcher = new BinarySearch();
    }

    @Test
    public void should_ReturnNegativeValue_When_ArrayIsNull() {
        // given
        double[] nums = null;
        double toFind = 0;

        // when
        int result = searcher.search(nums, toFind);

        // then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_ArrayIsEmpty() {
        // given
        double[] nums = {};
        double toFind = 0;

        // when
        int result = searcher.search(nums, toFind);

        // then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasOneElem() {
        // given
        double[] nums = { 1 };
        double correctNumToFind = 1;

        // when
        int result = searcher.search(nums, correctNumToFind);

        // then
        int expected = 0;
        assertEquals(expected, result);
    }

    public void should_ReturnNegativeValue_When_ElemNotExistInOneElemArray() {
        // given
        double[] nums = { 1 };
        double incorrectNumToFind = 2;

        // when
        int result = searcher.search(nums, incorrectNumToFind);

        // then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasTwoElems() {
        // given
        double[] nums = { 1, 3 };
        double correctNumToFind = 3;

        // when
        int result = searcher.search(nums, correctNumToFind);

        // then
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasOddNumOfElems() {
        // given
        double[] nums = { -3, -1, 1, 3, 5 };
        double correctNumToFind = 3;

        // when
        int result = searcher.search(nums, correctNumToFind);

        // then
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectIndex_When_ArrayHasEvenNumOfElems() {
        // given
        double[] nums = { -3, -1, 1, 3, 5, 7, 9 };
        double correctNumToFind = 7;

        // when
        int result = searcher.search(nums, correctNumToFind);

        // then
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnFirstIndex_When_SearchingMinNumFromArray() {
        // given
        double[] nums = { 0, 1, 2, 3, 4, 5 };
        double minNumToFind = 0;

        // when
        int result = searcher.search(nums, minNumToFind);

        // then
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnLastIndex_When_SearchingMaxNumFromArray() {
        // given
        double[] nums = { 0, 1, 2, 3, 4, 5 };
        double maxNumToFind = 5;

        // when
        int result = searcher.search(nums, maxNumToFind);

        // then
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_SearchingSmallerThanMinNumFromArray() {
        // given
        double[] nums = { 0, 1, 2, 3, 4, 5 };
        double smallerNumToFind = -10;

        // when
        int result = searcher.search(nums, smallerNumToFind);

        // then
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnNegativeValue_When_SearchingBiggerThanMaxNumFromArray() {
        // given
        double[] nums = { 0, 1, 2, 3, 4, 5 };
        double biggerNumToFind = 10;

        // when
        int result = searcher.search(nums, biggerNumToFind);

        // then
        int expected = -1;
        assertEquals(expected, result);
    }

}