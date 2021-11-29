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
        assertEquals(expected, actual);
    }

    @Test
    public void should_CorrectlyGetValues_WhenExistInMap(){
        // given
        double toGet = 1.0;

        // when
        rbtMap.setValue(toGet, toGet);
        double actualKey = rbtMap.getValue(toGet);

        // then
        double expectedKey = toGet;
        assertEquals(expectedKey, actualKey, 0);
    }

    @Test
    public void should_CorrectlyGetManyValues_WhenExistInMap(){
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
    public void should_CorrectlyGetValues_WhenNotExistInMap(){
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
    public void should_CorrectlyDeletedKey_WhenExistInMap() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        rbtMap.deleteMax();
        String actualInOrder = rbtMap.getInOrder();

        // then
        String expectedInOrder = "5.0:5.0 8.0:8.0 15.0:15.0 17.0:17.0 18.0:18.0 25.0:25.0 30.0:30.0 ";
        assertEquals(expectedInOrder, actualInOrder);
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
        for (int i = 0; i < testLength + 10; i++){
            rbtMap.deleteMax();
        }
        String actualInOrderAfterDelete = rbtMap.getInOrder();

        // then
        String expectedInOrderBeforeDelete = "";
        String expectedInOrderAfterDelete = "";
        assertEquals(expectedInOrderAfterDelete, actualInOrderAfterDelete);
    }

    @Test
    public void should_CorrectlyTraverseTree_InOrder() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        String actualInOrder = rbtMap.getInOrder();

        // then
        String expectedInOrder = "5.0:5.0 8.0:8.0 15.0:15.0 17.0:17.0 18.0:18.0 25.0:25.0 30.0:30.0 80.0:80.0 ";
        assertEquals(expectedInOrder, actualInOrder);
    }

    @Test
    public void should_CorrectlyTraverseTree_PostOrder() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        String actualPostOrder = rbtMap.getPostOrder();

        // then
        String expectedPostOrder = "5.0:5.0 15.0:15.0 8.0:8.0 18.0:18.0 30.0:30.0 80.0:80.0 25.0:25.0 17.0:17.0 ";
        assertEquals(expectedPostOrder, actualPostOrder);
    }

    @Test
    public void should_CorrectlyTraverseTree_PreOrder() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        String actualPreOrder = rbtMap.getPreOrder();

        // then
        String expectedPreOrder = "17.0:17.0 8.0:8.0 5.0:5.0 15.0:15.0 25.0:25.0 18.0:18.0 80.0:80.0 30.0:30.0 ";
        assertEquals(expectedPreOrder, actualPreOrder);
    }



    @Test
    public void should_CorrectlyMakeLeftSwap() {
        // given
        double[] addSequence = { 1.0, 2.0 };

        // when
        rbtMap.setValue(addSequence[0], addSequence[0]);
        String actualBeforeLeftSwap = rbtMap.getPreOrder();
        rbtMap.setValue(addSequence[1], addSequence[1]);
        String actualAfterLeftSwap = rbtMap.getPreOrder();

        // then
        String expectedBeforeLeftSwap = "1.0:1.0 ";
        String expectedAfterLeftSwap = "2.0:2.0 1.0:1.0 ";
        assertEquals(expectedBeforeLeftSwap, actualBeforeLeftSwap);
        assertEquals(expectedAfterLeftSwap, actualAfterLeftSwap);

    }

    @Test
    public void should_CorrectlyMakeRightSwap() {
        // given
        double[] addSequence = { 3.0, 2.0, 1.0 };

        // when
        rbtMap.setValue(addSequence[0], addSequence[0]);
        rbtMap.setValue(addSequence[1], addSequence[1]);
        String actualBeforeRightSwap = rbtMap.getPreOrder();
        rbtMap.setValue(addSequence[2], addSequence[2]);
        String actualAfterRightSwap = rbtMap.getPreOrder();

        // then
        String expectedBeforeRightSwap = "3.0:3.0 2.0:2.0 ";
        String expectedAfterRightSwap = "2.0:2.0 1.0:1.0 3.0:3.0 ";
        assertEquals(expectedBeforeRightSwap, actualBeforeRightSwap);
        assertEquals(expectedAfterRightSwap, actualAfterRightSwap);
    }

    @Test
    public void should_CorrectlyMakeLeftAndRightSwap() {
        // given
        double[] addSequence = { 3.0, 1.0, 2.0 };

        // when
        rbtMap.setValue(addSequence[0], addSequence[0]);
        rbtMap.setValue(addSequence[1], addSequence[1]);
        String actualBeforeSwap = rbtMap.getPreOrder();
        rbtMap.setValue(addSequence[2], addSequence[2]);
        String actualAfterSwap = rbtMap.getPreOrder();

        // then
        String expectedBeforeSwap = "3.0:3.0 1.0:1.0 ";
        String expectedAfterSwap = "2.0:2.0 1.0:1.0 3.0:3.0 ";
        assertEquals(expectedBeforeSwap, actualBeforeSwap);
        assertEquals(expectedAfterSwap, actualAfterSwap);
    }

    @Test
    public void should_CorrectlyMakeRightAndLeftSwap() {
        // given
        double[] addSequence = { 3.0, 1.0, 2.0 };

        // when
        rbtMap.setValue(addSequence[0], addSequence[0]);
        rbtMap.setValue(addSequence[1], addSequence[1]);
        String actualAfterLeftBeforeRightSwap = rbtMap.getPreOrder();
        rbtMap.setValue(addSequence[2], addSequence[2]);
        String actualAfterLeftAfterRightSwap = rbtMap.getPreOrder();

        // then
        String expectedAfterLeftBeforeRightSwap = "3.0:3.0 1.0:1.0 ";
        String expectedAfterLeftAfterRightSwap = "2.0:2.0 1.0:1.0 3.0:3.0 ";
        assertEquals(expectedAfterLeftBeforeRightSwap, actualAfterLeftBeforeRightSwap);
        assertEquals(expectedAfterLeftAfterRightSwap, actualAfterLeftAfterRightSwap);
    }

    @Test
    public void should_CorrectlyOrganizeTree() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            rbtMap.setValue(added, added);
        }
        String actualSwap = rbtMap.getPreOrder();

        // then
        String expectedSwap = "17.0:17.0 8.0:8.0 5.0:5.0 15.0:15.0 25.0:25.0 18.0:18.0 80.0:80.0 30.0:30.0 ";
        assertEquals(expectedSwap, actualSwap);
    }
}
