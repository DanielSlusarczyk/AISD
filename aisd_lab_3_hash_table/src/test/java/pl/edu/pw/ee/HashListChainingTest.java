package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    private HashListChaining<Double> hashListChaining;
    private int size = 100;

    @Before
    public void setUp() {
        hashListChaining = new HashListChaining<>(size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void size_setToZero() {
        hashListChaining = new HashListChaining<>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void size_setToNegativeNumber() {
        hashListChaining = new HashListChaining<>(-1);
    }

    @Test
    public void add_oneElement() {
        // given
        double value = 10.0;

        // when
        hashListChaining.add(value);

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void add_twoElements() {
        // given
        double[] value = { 10.0, 15.0 };

        // when
        hashListChaining.add(value[0]);
        hashListChaining.add(value[1]);

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void add_manyDifferentElements() {
        // given
        int testLength = 10_000;
        long SEED = 1410;
        Random random = new Random(SEED);
        List<Double> addedValues = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            double toAdd = random.nextDouble();
            while (addedValues.contains(toAdd)) {
                toAdd = random.nextDouble();
            }
            hashListChaining.add(random.nextDouble());
            addedValues.add(toAdd);
        }

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = testLength;
        assertEquals(expected, actual);
    }

    @Test
    public void add_manyEqualElements() {
        // given
        int testLength = 10_000;
        double value = 0.0;

        // when
        for (int i = 0; i < testLength; i++) {
            hashListChaining.add(value);
        }

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void add_andDeleteOneElements() {
        // given
        double value = 10.0;

        // when
        hashListChaining.add(value);
        hashListChaining.delete(value);

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void add_andDeleteManyElements() {
        // given
        int testLength = 10_000;

        // when
        for (int i = 0; i < testLength; i++) {
            hashListChaining.add(Double.valueOf(i));
        }
        for (int i = 0; i < testLength; i++) {
            hashListChaining.delete(Double.valueOf(i));
        }

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_null() {
        hashListChaining.get(null);
    }

    @Test
    public void get_unaddedElement() {
        // given
        double getValue = 10.0;
        double addValue = 0.0;

        // when
        hashListChaining.add(addValue);

        // then
        Object actual = hashListChaining.get(getValue);
        assertNull(actual);
    }

    @Test
    public void get_addedElement() {
        // given
        double value = 10.0;

        // when
        hashListChaining.add(value);

        // then
        double actual = hashListChaining.get(value);
        double expected = value;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void get_elementFromEmpty() {
        // given
        double value = 10.0;

        // when

        // then
        Object actual = hashListChaining.get(value);
        assertNull(actual);
    }

    @Test
    public void get_elementFromManyElements() {
        // given
        int testLength = 10_000;
        double value = 0.0;
        long SEED = 1410;
        int indexToGet = new Random(SEED).nextInt(testLength);
        Random random = new Random(SEED);

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if (i == indexToGet) {
                value = randomDouble;
            }
        }

        // then
        double actual = hashListChaining.get(value);
        double expected = value;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void get_deletedElement() {
        // given
        int testLength = 10_000;
        double value = 0.0;
        long SEED = 1410;
        int indexToGet = new Random(SEED).nextInt(testLength);
        Random random = new Random(SEED);

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if (i == indexToGet) {
                value = randomDouble;
            }
        }
        hashListChaining.delete(value);

        // then
        Object actual = hashListChaining.get(value);
        assertNull(actual);
    }

    @Test
    public void delete_allElements() {
        // given
        int testLength = 10_000;
        double addedValues[] = new double[testLength];
        long SEED = 1410;
        Random random = new Random(SEED);

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            addedValues[i] = randomDouble;
        }
        for (int i = 0; i < testLength; i++) {
            hashListChaining.delete(addedValues[i]);
        }

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void delete_andGetDeletedElement() {
        // given
        int testLength = 100;
        double value = 0.0;
        long SEED = 1410;
        int indexToGet = new Random(SEED).nextInt(testLength);
        Random random = new Random(SEED);

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            hashListChaining.add(randomDouble);
            if (i == indexToGet) {
                value = randomDouble;
            }
        }
        hashListChaining.delete(value);

        // then
        Object actual = hashListChaining.get(value);
        assertNull(actual);
    }

    @Test
    public void delete_notIncludedElement() {
        // given
        double value = 1.0;

        // when
        hashListChaining.add(0.5);
        hashListChaining.delete(value);

        // then
        int actual = hashListChaining.getNumberOfElements();
        int expected = 1;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void countLoadFactor_addOneElement() {
        // given
        double valueToAdd = 1.0;

        // when
        hashListChaining.add(valueToAdd);

        // then
        double actual = hashListChaining.countLoadFactor();
        double expected = valueToAdd / size;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void countLoadFactor_addManyElements() {
        // given
        int testLength = 10_000;
        long SEED = 1410;
        int toAdd = new Random(SEED).nextInt(testLength);
        Random random = new Random(SEED);

        // when
        for (int i = 0; i < toAdd; i++) {
            hashListChaining.add(random.nextDouble());
        }

        // then
        double actual = hashListChaining.countLoadFactor();
        double expected = Double.valueOf(toAdd) / size;
        assertEquals(expected, actual, 0);
    }
}
