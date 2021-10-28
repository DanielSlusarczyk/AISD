package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    private HashListChaining<Double> hashListChaining;
    private int size = 4096;

    @Before
    public void setUp(){
        hashListChaining = new HashListChaining<>(size);
    }

    @Test
    public void getAddedElement(){
        //given
        double value = 10;
        //when
        hashListChaining.add(value);
        double actual = hashListChaining.get(value);
        //then
        double expected = value;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void getAddedElement_afterDoubleAdd(){
        //given
        double value = 10;
        hashListChaining.add(value);
        hashListChaining.add(value);
        //then
        Object expected = hashListChaining.get(value);
        assertEquals(expected, value);
    }

}
