package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {
    private String pattern = "ABCB";
    private DeterministicFiniteAutomatonTextSearch dATS = new DeterministicFiniteAutomatonTextSearch(pattern);

    @Test (expected = IllegalArgumentException.class)
    public void schould_throwException_whenPattternIsNull() {
        // given
        String pattern = null;

        // then
        dATS = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        assert false;
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenPatternIsEmpty() {
        // given
        String pattern = "";

        // then
        dATS = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        assert false;
    }

    @Test 
    public void findFirst_should_findProperIndex() {
        // given
        String pattern = "AB";
        String text = "ABC";

        // then
        dATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = dATS.findFirst(text);

        // when
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test 
    public void findAll_should_findProperIndex() {
        // given
        String pattern = "AB";
        String text = "ABCAB";

        // then
        dATS = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = dATS.findAll(text);

        // when
        int[] expectedIndexes = {0, 3};
        assertArrayEquals(expectedIndexes, actualIndexes);
    }
}
