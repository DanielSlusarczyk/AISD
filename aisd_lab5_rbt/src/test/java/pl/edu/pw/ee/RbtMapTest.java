package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

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
    public void should_ThrowException_WhenPutValueNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.setValue("key", null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPutKeyNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.setValue(null, "value");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGetNull() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.getValue(null);

        // then
        assert false;
    }

    @Test
    public void should_DoNothing_WhenDeleteFromEmptyMap() {
        // given
        RbtMap<String, String> rbtMap = new RbtMap<>();

        // when
        rbtMap.deleteMax();

        // then
        assert true;
    }

    @Test
    public void should_CorrectlySetValue_WhenNotExistInMap() {
        // given
        Double toAdd = 1.0;

        // when
        rbtMap.setValue(toAdd, toAdd);
        String actual = rbtMap.getInOrder().trim();

        // then
        String expected = "1.0:1.0";
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlySetValues_WhenNotExistInMap() {
        // given
        Double toAdd[] = { 1.0, 2.0, 3.0, 5.0, 6.0 };

        // when
        for (Double added : toAdd) {
            rbtMap.setValue(added, added);
        }
        String actual = rbtMap.getInOrder().trim();

        // then
        String expected = "1.0:1.0 2.0:2.0 3.0:3.0 5.0:5.0 6.0:6.0";
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlySetManyValues_WhenNotExistInMap() {
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
        String actual = rbtMap.getInOrder();

        // then
        doubleList.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });
        String expected = "";
        for (Double element : doubleList) {
            System.out.println("S");
            expected = expected + element + ":" + element + " ";
        }
        expected = expected.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlySetValues_WhenExistInMap() {
        // given
        Double toAdd[] = { 1.0, 1.0, 1.0, 1.0, 1.0 };

        // when
        for (Double added : toAdd) {
            rbtMap.setValue(added, added);
        }
        String actual = rbtMap.getInOrder().trim();

        // then
        String expected = "1.0:1.0";
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlySetManyValues_WhenExistInMap() {
        // given
        int testLength = 100;
        double randomDouble = random.nextDouble();

        // when
        for (int i = 0; i < testLength; i++) {
            rbtMap.setValue(randomDouble, randomDouble);
        }
        String actual = rbtMap.getInOrder();

        // then
        String expected = randomDouble + ":" + randomDouble + " ";
        expected = expected.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyGetValues_WhenExistInMap() {
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
    public void should_CorrectlyGetManyValues_WhenExistInMap() {
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
    public void should_CorrectlyGetValues_WhenNotExistInMap() {
        // given
        double toGet = 1.0;

        // when
        Object actualKey = rbtMap.getValue(toGet);

        // then
        assertNull(actualKey);
    }

    @Test
    public void should_CorrectlyDeletedKey_WhenMapIsEmpty() {
        // given

        // when
        rbtMap.deleteMax();
        String actualInOrder = rbtMap.getInOrder();

        // then
        String expectedInOrder = "";
        assertEquals(expectedInOrder, actualInOrder);
    }

    @Test
    public void should_CorrectlyDeletedKey_WhenMapHasOneElement() {
        // given
        Double toAdd = 1.0;

        // when
        rbtMap.setValue(toAdd, toAdd);
        rbtMap.deleteMax();
        String actualInOrder = rbtMap.getInOrder();

        // then
        String expectedInOrder = "";
        assertEquals(expectedInOrder, actualInOrder);
    }

    @Test
    public void should_CorrectlyDeletedKey_WhenExistInMap() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 30.0, 80.0, 25.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        String actualInOrderBeforeDelete = rbtMap.getInOrder();
        rbtMap.deleteMax();
        String actualInOrderAfterDelete = rbtMap.getInOrder();

        // then
        String expectedInOrderBeforeDelete = "5.0:5.0 8.0:8.0 15.0:15.0 17.0:17.0 18.0:18.0 25.0:25.0 30.0:30.0 80.0:80.0";
        String expectedInOrderAfterDelete = "5.0:5.0 8.0:8.0 15.0:15.0 17.0:17.0 18.0:18.0 25.0:25.0 30.0:30.0";
        assertEquals(expectedInOrderBeforeDelete, actualInOrderBeforeDelete);
        assertEquals(expectedInOrderAfterDelete, actualInOrderAfterDelete);
    }

    @Test
    public void deletedKey_shouldNotExistInMap_AfterDelete() {
        // given
        int testLength = 100;
        List<Double> doubleList = new ArrayList<>();
        Double maxElement = -1.0;

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }
            if (randomDouble > maxElement) {
                maxElement = randomDouble;
            }

            doubleList.add(randomDouble);
            rbtMap.setValue(randomDouble, randomDouble);
        }

        Object actualValueBeforeDelete = rbtMap.getValue(maxElement);
        rbtMap.deleteMax();
        Object actualValueAfterDelete = rbtMap.getValue(maxElement);

        // then
        Object expectedValueBeforeDelete = maxElement;
        Object expectedValueAfterDelete = null;
        assertEquals(expectedValueBeforeDelete, actualValueBeforeDelete);
        assertEquals(expectedValueAfterDelete, actualValueAfterDelete);
    }

    @Test
    public void should_CorrectlyDeletedAllKey() {
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
        String actualInOrderBeforeDelete = rbtMap.getInOrder();
        for (int i = 0; i < testLength; i++) {
            rbtMap.deleteMax();
        }
        String actualInOrderAfterDelete = rbtMap.getInOrder();

        // then
        String expectedInOrderBeforeDelete = "";
        doubleList.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });
        for (Double x : doubleList) {
            expectedInOrderBeforeDelete = expectedInOrderBeforeDelete + x + ":" + x + " ";
        }
        expectedInOrderBeforeDelete = expectedInOrderBeforeDelete.trim();
        String expectedInOrderAfterDelete = "";
        assertEquals(expectedInOrderBeforeDelete, actualInOrderBeforeDelete);
        assertEquals(expectedInOrderAfterDelete, actualInOrderAfterDelete);
    }

}
