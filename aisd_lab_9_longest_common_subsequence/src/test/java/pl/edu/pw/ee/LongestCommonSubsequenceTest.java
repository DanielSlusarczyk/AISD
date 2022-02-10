package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class LongestCommonSubsequenceTest {
    private LongestCommonSubsequence longestCommonSubsequence;
    private String pathToTestDir = "src/test/java/pl/edu/pw/ee/expectedOut";
    private String nameOFileWithExpectedOut;
    private File fileWithExpectedOut;
    private InputStreamReader reader;

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFirstStringIsNull() {
        // given
        String firstStr = null;
        String secondStr = "A";

        // when
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenSecondStringIsNull() {
        // given
        String firstStr = "A";
        String secondStr = null;

        // when
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenBothStringsAreNull() {
        // given
        String firstStr = null;
        String secondStr = null;

        // when
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // then
    }

    @Test
    public void should_correctlyReturnLCS_whenFristStringIsEmpty() {
        // given
        String firstStr = "";
        String secondStr = "ABA";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenSecondStringIsEmpty() {
        // given
        String firstStr = "ABA";
        String secondStr = "";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenBothStringsAreEmpty() {
        // given
        String firstStr = "";
        String secondStr = "";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenFirstStringHasOneSign() {
        // given
        String firstStr = "A";
        String secondStr = "BBABB";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "A";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenSecondStringHasOneSign() {
        // given
        String firstStr = "BBABB";
        String secondStr = "A";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "A";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenBothStringsHaveOneSign() {
        // given
        String firstStr = "A";
        String secondStr = "A";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "A";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenIsOnlyOneLCS() {
        // given
        String firstStr = "PREZENT";
        String secondStr = "REZERWAT";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "REZET";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenAreTwoSameLCS() {
        // given
        String firstStr = "  AB  AB  ";
        String secondStr = "AB";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "AB";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyReturnLCS_whenAreDifferentLCS() {
        // given
        String firstStr = "   ABC    CDF  ";
        String secondStr = "___CDF___ABC___";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "CDF";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_beCaseSensitive() {
        // given
        String firstStr = "aAaAaAbBbBbB";
        String secondStr = "AABB";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);

        // when
        String actualLCS = longestCommonSubsequence.findLCS();

        // then
        String expectedLCS = "AABB";
        assertEquals(expectedLCS, actualLCS);
    }

    @Test
    public void should_correctlyPrint_whenBothStringsAreEmpty() {
        // given
        String firstStr = "";
        String secondStr = "";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_whenBothStringsAreEmpty.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_whenFirstStringIsEmpty() {
        // given
        String firstStr = "";
        String secondStr = "AABB";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_whenFirstStringIsEmpty.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_whenSecondStringIsEmpty() {
        // given
        String firstStr = "AABB";
        String secondStr = "";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_whenSecondStringIsEmpty.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_specialSigns() {
        // given
        String firstStr = "\n \t\r";
        String secondStr = "\n\n \n";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_specialSigns.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_twoDigitNumbers() {
        // given
        String firstStr = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String secondStr = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_twoDigitNumbers.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_whenIsOnlyOneLCS() {
        // given
        String firstStr = "PREZENT";
        String secondStr = "REZERWAT";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_whenIsOnlyOneLCS.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_whenAreDifferentLCS() {
        // given
        String firstStr = "     ABC       DEF     ";
        String secondStr = "------DEF-----ABC----";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_whenAreDifferentLCS.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    @Test
    public void should_correctlyPrint_longStrings() {
        // given
        String firstStr = "często_z_odkrywaniem";
        String secondStr = "rzeczy_nie_trzeba\n_się_spieszyć";
        longestCommonSubsequence = new LongestCommonSubsequence(firstStr, secondStr);
        nameOFileWithExpectedOut = "should_correctlyPrint_longStrings.txt";

        // when
        validateInput(pathToTestDir);
        char[][] actualOut = longestCommonSubsequence.getArrayToDisplay();

        // then
        assertTrue(checkSameness(actualOut));
    }

    private boolean checkSameness(char[][] actualValue) {
        try {
            reader = new InputStreamReader(new FileInputStream(fileWithExpectedOut), StandardCharsets.UTF_8);
            int row = 0;
            int column = 0;
            int actualIntChar = reader.read();
            while (actualIntChar != -1) {
                char actualChar = (char) actualIntChar;
                if (Character.compare(actualValue[row][column], actualChar) != 0) {
                    reader.close();
                    return false;
                }

                column++;
                if (actualChar == '\n') {
                    row++;
                    column = 0;
                }
                actualIntChar = reader.read();
            }
            reader.close();
            return true;
        } catch (IOException | IndexOutOfBoundsException exception) {
            return false;
        }
    }

    private void validateInput(String pathToRootDir) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to test dir is null");
        }
        File verifiedFile = new File(pathToRootDir);
        if (!verifiedFile.isDirectory()) {
            throw new IllegalArgumentException("Path does not lead to a test directory");
        }
        if (!verifiedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from test directory");
        }
        if (nameOFileWithExpectedOut != null) {
            fileWithExpectedOut = new File(verifiedFile.getPath() + "\\" + nameOFileWithExpectedOut);
            if (!fileWithExpectedOut.exists() || !fileWithExpectedOut.canRead()) {
                throw new IllegalArgumentException("There is problem with file for tests");
            }
        }
    }
}
