package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    private HashListChaining<Double> hashListChaining;
    private int size = 100;

    @Before
    public void setUp(){
        hashListChaining = new HashListChaining<>(size);
    }

    @Test
    public void add_oneElement(){
        //given
        double value = 10.0;

        //when
        hashListChaining.add(value);

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void add_twoElements(){
        //given
        double[] value = {10.0, 15.0};

        //when
        hashListChaining.add(value[0]);
        hashListChaining.add(value[1]);

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void add_manyDifferentElements(){
        //given
        int testLength = 100;

        //when
        for(int i = 0; i < testLength; i++){
            hashListChaining.add(new Random().nextDouble());
        }

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = testLength;
        assertEquals(expected, actual);
    }

    @Test
    public void add_manyEqualElements(){
        //given
        int testLength = 100;
        double value = 0.0;

        //when
        for(int i = 0; i < testLength; i++){
            hashListChaining.add(value);
        }

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void add_addAndDeleteOneElements(){
        //given
        double value = 10.0;

        //when
        hashListChaining.add(value);
        hashListChaining.delete(value);

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public void add_addAndDeleteManyElements(){
        //given
        int testLength = 100;
        double value = 0.0;

        //when
        for(int i = 0; i < testLength; i++){
            hashListChaining.add(value);
        }
        for(int i = 0; i < testLength; i++){
            hashListChaining.delete(value);
        }

        //then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void get_element(){
        //given
        double value = 10.0;
        double addValue = 0.0;

        //when
        hashListChaining.add(addValue);
        Object actual = hashListChaining.get(value);

        //then
        assertNull(actual);
    }

    @Test
    public void get_addedElement(){
        //given
        double value = 10.0;

        //when
        hashListChaining.add(value);
        double actual = hashListChaining.get(value);

        //then
        double expected = value;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void get_elementFromEmpty(){
        //given
        double value = 10.0;

        //when
        Object actual = hashListChaining.get(value);

        //then
        assertNull(actual);
    }

    @Test
    public void get_elementFromManyElements(){
        //given
        int testLength = 100;
        double value = 0.0;
        int indexToGet = new Random().nextInt(testLength);
        Random random = new Random();

        //when
        for(int i = 0; i < testLength; i++){
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if(i==indexToGet){
                value = randomDouble;
            }
        }
        double actual = hashListChaining.get(value);

        //then
        double expected = value;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void get_deletedElement(){
        //given
        int testLength = 100;
        double value = 0.0;
        int indexToGet = new Random().nextInt(testLength);
        Random random = new Random();

        //when
        for(int i = 0; i < testLength; i++){
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if(i==indexToGet){
                value = randomDouble;
            }
        }
        hashListChaining.delete(value);
        Object actual = hashListChaining.get(value);

        //then
        assertNull(actual);
    }

    @Test
    public void delete_allElements(){
        //given
        int testLength = 100;
        double addedValues[] = new double[testLength];
        Random random = new Random();

        //when
        for(int i = 0; i < testLength; i++){
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            addedValues[i] = randomDouble;
        }
        for(int i = 0; i < testLength; i++){
            hashListChaining.delete(addedValues[i]);
        }
        int actual = hashListChaining.getNumberOfElements();

        //then
        int expected = 0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void delete_getDeletedElement(){
        //given
        int testLength = 100;
        double value = 0.0;
        int indexToGet = new Random().nextInt(testLength);
        Random random = new Random();

        //when
        for(int i = 0; i < testLength; i++){
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if(i==indexToGet){
                value = randomDouble;
            }
        }
        hashListChaining.delete(value);
        Object actual = hashListChaining.get(value);

        //then
        assertNull(actual);
    }
}
