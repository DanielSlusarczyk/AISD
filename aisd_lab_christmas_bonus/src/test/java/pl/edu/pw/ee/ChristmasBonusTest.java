package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChristmasBonusTest {
    private ChristmasBonus longestRepeat;

    @Before
    public void setUp() {
        longestRepeat = new ChristmasBonus();
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenScheduleIsNull() {
        // given
        int[] schedule = null;
        int changes = 0;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenLengthOfScheduleIsTooBig() {
        // given
        int[] schedule = new int[100000];
        int changes = 0;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenNumberOfChangesIsTooLow() {
        // given
        int[] schedule = { 1 };
        int changes = -1;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenNumberOfChangesIsTooHigh() {
        // given
        int[] schedule = { 1, 2, 3 };
        int changes = schedule.length + 1;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenScheduleContainsNegativeNumber() {
        // given
        int[] schedule = { 1, 2, 3, -4, 2 };
        int changes = 0;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenScheduleContainsZero() {
        // given
        int[] schedule = { 1, 2, 3, 0, 2 };
        int changes = 0;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenScheduleContainsTooHighNumber() {
        // given
        int[] schedule = { 1, 2, 3, 100000, 2 };
        int changes = 0;

        // when
        longestRepeat.findMaxLength(schedule, changes);

        // then
        assert false;
    }

    @Test
    public void schould_findLongestSubarray_whenScheduleIsEmpty() {
        // given
        int[] schedule = {};
        int changes = 0;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 0;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenScheduleHasOneElement() {
        // given
        int[] schedule = { 1 };
        int changes = 0;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 1;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenScheduleHasTwoElements() {
        // given
        int[] schedule = { 1, 2 };
        int changes = 0;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 1;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenScheduleHasTwoEqualElements() {
        // given
        int[] schedule = { 2, 2 };
        int changes = 0;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 2;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenChangesAreUnnecessary() {
        // given
        int[] schedule = { 3, 3, 3, 3, 3, 3, 3 };
        int changes = 5;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = schedule.length;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenAreTwoSolutions() {
        // given
        int[] schedule = { 1, 1, 2, 1, 1, 5, 5, 5, 5, 4 };
        int changes = 1;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 5;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenIsOneSolution() {
        // given
        int[] schedule = { 4, 4, 4, 4, 1, 2, 3, 4, 4, 4 };
        int changes = 3;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = schedule.length;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenIsZeroChanges() {
        // given
        int[] schedule = { 1, 1, 2, 1, 1, 5, 5, 5, 5, 4 };
        int changes = 0;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = 4;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenEntireArrayCanBeChanged() {
        // given
        int[] schedule = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int changes = schedule.length;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = schedule.length;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_whenScheduleIsAlternating() {
        // given
        int[] schedule = { 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3 };
        int changes = schedule.length / 2;

        // when
        int actualLength = longestRepeat.findMaxLength(schedule, changes);

        // then
        int expectedLength = schedule.length;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void schould_findLongestSubarray_inDifferentCases() {
        // given
        int[] schedule = { 2, 2, 2, 2, 2, 3, 2, 2, 3, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 3, 3, 3, 3 };

        // when
        int[] actualResults = new int[5];
        for (int changes = 0; changes < 5; changes++) {
            actualResults[changes] = longestRepeat.findMaxLength(schedule, changes);
        }

        // then
        int[] expectedResults = { 5, 8, 12, 13, 19 };
        assertArrayEquals(expectedResults, actualResults);
    }
}
