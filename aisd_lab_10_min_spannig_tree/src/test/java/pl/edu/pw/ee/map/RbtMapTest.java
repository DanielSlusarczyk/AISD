package pl.edu.pw.ee.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Node;

public class RbtMapTest {
    private RbtMap<Double, Double> rbtMap;
    private long SEED = 1410;
    private Random random;

    @Before
    public void setUp() {
        rbtMap = new RbtMap<>();
        random = new Random(SEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenValueIsNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.setValue("key", null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenKeyIsNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.setValue(null, "value");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGetIsNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.getValue(null);

        // then
        assert false;
    }

    @Test
    public void should_correctlyGetValues_whenExistInMap() {
        // given
        double toAdd = 1.0;
        double toGet = toAdd;

        // when
        rbtMap.setValue(toAdd, toAdd);
        double actualKey = rbtMap.getValue(toGet);

        // then
        double expectedKey = toGet;
        assertEquals(expectedKey, actualKey, 0);
    }

    @Test
    public void should_correctlyGetManyValues_whenExistInMap() {
        // given
        int testLength = 100;
        List<Double> doubleList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }

            doubleList.add(randomDouble);
            rbtMap.setValue(randomDouble, randomDouble);
        }

        // then
        for (int i = 0; i < testLength; i++) {
            double expectedValue = doubleList.get(i);
            double actualValue = rbtMap.getValue(doubleList.get(i));
            assertEquals(expectedValue, actualValue, 0);
        }
    }

    @Test
    public void should_correctlyGetValues_whenNotExistInMap() {
        // given
        double toGet = 1.0;

        // when
        Object actualKey = rbtMap.getValue(toGet);

        // then
        assertNull(actualKey);
    }

    @Test
    public void should_correctlyGetValues_whenMapHasEqualElements() {
        // given
        int testLength = 100;
        Edge edge = new Edge(new Node("B"), new Node("A"), 10);
        RbtMap<Edge, Edge> edgeMap = new RbtMap<>();

        // when
        for (int i = 0; i < testLength; i++) {
            edgeMap.setValue(edge, edge);
        }
        Edge actualValue = edgeMap.getValue(edge);

        // then
        Edge expectedValue = edge;
        if (expectedValue.compareTo(actualValue) == 0) {
            assert true;
        } else {
            assert false;
        }
    }
}
