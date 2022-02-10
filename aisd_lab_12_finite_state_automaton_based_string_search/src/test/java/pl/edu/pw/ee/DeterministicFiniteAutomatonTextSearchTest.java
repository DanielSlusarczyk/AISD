package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {
    private String pattern = "ABCB";
    private DeterministicFiniteAutomatonTextSearch searcher = new DeterministicFiniteAutomatonTextSearch(pattern);

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPattternIsNull() {
        // given
        String pattern = null;

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPatternIsEmpty() {
        // given
        String pattern = "";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);

        // when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenTextIsNull_findFirst() {
        // given
        String text = null;

        // then
        searcher.findFirst(text);

        // when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenTextIsNull_findAll() {
        // given
        String text = null;

        // then
        searcher.findAll(text);

        // when
        assert false;
    }

    @Test
    public void should_findFirst_patternAtIndexZero() {
        // given
        String pattern = "AB";
        String text = "ABC";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_patternAtIndexOne() {
        // given
        String pattern = "AB";
        String text = "CABC";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 1;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_patternAtLastIndexes() {
        // given
        String pattern = "AB";
        String text = "AAAAAAAAAAB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 9;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_patternIsText() {
        // given
        String pattern = "ABBABA";
        String text = pattern;

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_oneLetterPattern() {
        // given
        String pattern = "A";
        String text = "BBBBBBBBABBBBBBBB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 8;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_textWithPolishDiacritics() {
        // given
        String pattern = "gęś";
        String text = "Zażółć gęślą jaźń";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 7;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_textWithSpecialSigns() {
        // given
        String pattern = "\t\r";
        String text = "a\n\n\t\t\r\ra\n\n";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 4;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findFirst_textWithPunctuationMarks() {
        // given
        String pattern = "%$";
        String text = ",.?<>/:;\"\'+-_=*/)(*&^%$#@!";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = 21;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_returnNegativeValue_textIsEmpty() {
        // given
        String pattern = "A";
        String text = "";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = -1;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_returnNegativeValue_textWithoutPattern() {
        // given
        String pattern = "ABA";
        String text = "BAAABBBBBAAAA";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = -1;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_returnNegativeValue_patternLongerThanText() {
        // given
        String pattern = "ABAABABABA";
        String text = "ABA";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int actualIndex = searcher.findFirst(text);

        // when
        int expectedIndex = -1;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void should_findAll_patternOnlyAtIndexZero() {
        // given
        String pattern = "AB";
        String text = "ABBBB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 0 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_patternOnlyAtIndexOne() {
        // given
        String pattern = "AB";
        String text = "AABBBB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 1 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_patternOnlyAtLastIndex() {
        // given
        String pattern = "AB";
        String text = "BAACBBBAB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 7 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_patternIsText() {
        // given
        String pattern = "AABB";
        String text = pattern;

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 0 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_oneLetterPattern() {
        // given
        String pattern = "A";
        String text = "AAAAAAAAA";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_textWithPattern() {
        // given
        String pattern = "ABC";
        String text = "AAAABCBBBABCAAAAACABDABCD";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 3, 9, 21 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_overlappingPattern() {
        // given
        String pattern = "AABBAABB";
        String text = "AABBAABBAABB";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 0, 4 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_findAll_longerText() {
        // given
        String pattern = "nie";
        String text = "Mniej mam i mniemam że nie mam ja mienia" +
                "Mnie nie omamia mania mania mniemania." +
                "Ja mam imię, a nie mienienie się mianem." +
                "Ja manie mam na „nie”, a me imię – Niemanie.";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = { 1, 13, 23, 41, 45, 69, 93, 100, 103, 123, 135, 158 };
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_returnEmptyArray_textIsEmpty() {
        // given
        String pattern = "A";
        String text = "";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = {};
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_returnEmptyArray_textWithoutPattern() {
        // given
        String pattern = "L";
        String text = "ABCDEFGHIJK";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = {};
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    public void should_returnEmptyArray_patternLongerThanText() {
        // given
        String pattern = "AAAAAA";
        String text = "AAA";

        // then
        searcher = new DeterministicFiniteAutomatonTextSearch(pattern);
        int[] actualIndexes = searcher.findAll(text);

        // when
        int[] expectedIndexes = {};
        assertArrayEquals(expectedIndexes, actualIndexes);
    }
}
