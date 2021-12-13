package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    private LongestCommonSubsequence longestCommonSubsequence;

    @Before
    public void setUp(){
        
    }

    @Test
    public void should_returnLongestCommonSubsequence(){
        // given
        String first = "PREZENT";
        String second = "REZERWAT";
        longestCommonSubsequence = new LongestCommonSubsequence(first, second);

        // when
        String actualSubsequence = longestCommonSubsequence.findLCS();

        // then
        String  expectedSubsequence = "REZET";
        assertEquals(expectedSubsequence, actualSubsequence);
    }
}
