package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class HashLinearProbingTest {
    private HashOpenAdressing<Double> doubleHash;
    private HashOpenAdressing<String> stringHash;
    private long SEED = 1410;
    private Random random;

    @Before
    public void setUp() {
        doubleHash = new HashLinearProbing<>();
        stringHash = new HashLinearProbing<>();
        random = new Random(SEED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsZero() {
        // given
        int initialSize = 0;

        // when
        doubleHash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsNegative() {
        // given
        int initialSize = -1;

        // when
        doubleHash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGetNull() {
        // given

        // when
        stringHash.get(null);
        doubleHash.get(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenDeleteNull() {
        // given

        // when
        stringHash.delete(null);
        doubleHash.delete(null);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyDoubleSize_IfNeeded() {
        // given
        int testLength = 1000;
        int size = (int) ((testLength - 1)/doubleHash.getCorrectLoadFactor());
        doubleHash = new HashLinearProbing<>(size);
        List<Double> doubleList = new ArrayList<>();

        // when
        int sizeBefore = doubleHash.getSize();
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }

            doubleHash.put(random.nextDouble());
            doubleList.add(randomDouble);
        }
        int sizeAfter = doubleHash.getSize();

        // then
        int expectedBefore = size;
        int expectedAfter = size * 2;
        assertEquals(expectedBefore, sizeBefore);
        assertEquals(expectedAfter, sizeAfter);
    }

    @Test
    public void should_CorrectlyAddNewStringElems_WhenNotExistInHashTable() {
        // given
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = stringHash.getNElem();
        stringHash.put(newEleme);
        int nOfElemsAfterPut = stringHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 1;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddNewDoubleElems_WhenNotExistInHashTable() {
        // given
        double newEleme = 1.0;

        // when
        int nOfElemsBeforePut = doubleHash.getNElem();
        doubleHash.put(newEleme);
        int nOfElemsAfterPut = doubleHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 1;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddTwoNewStringElems_WhenNotExistInHashTable() {
        // given
        String firstEleme = "nothing special";
        String secondEleme = "nothing special too";

        // when
        int nOfElemsBeforePut = stringHash.getNElem();
        stringHash.put(firstEleme);
        stringHash.put(secondEleme);
        int nOfElemsAfterPut = stringHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 2;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddTwoNewDoubleElems_WhenNotExistInHashTable() {
        // given
        double firstEleme = 2.0;
        double secondEleme = 1.0;

        // when
        int nOfElemsBeforePut = doubleHash.getNElem();
        doubleHash.put(firstEleme);
        doubleHash.put(secondEleme);
        int nOfElemsAfterPut = doubleHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 2;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddNewStringElems_WhenExistInHashTable() {
        // given
        String firstEleme = "nothing special";
        String secondEleme = "nothing special";

        // when
        int nOfElemsBeforePut = stringHash.getNElem();
        stringHash.put(firstEleme);
        stringHash.put(secondEleme);
        int nOfElemsAfterPut = stringHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 1;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddManyNewStringElems_WhenNotExistInHashTable() {
        // given
        int testLength = 100;

        // when
        int nOfElemsBeforePut = stringHash.getNElem();
        for (int i = 0; i < testLength; i++) {
            stringHash.put(RandomStringUtils.randomAlphanumeric(10));
        }
        int nOfElemsAfterPut = stringHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = testLength;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddManyNewDoubleElems_WhenNotExistInHashTable() {
        // given
        int testLength = 100;

        // when
        int nOfElemsBeforePut = doubleHash.getNElem();
        for (int i = 0; i < testLength; i++) {
            doubleHash.put(random.nextDouble());
        }
        int nOfElemsAfterPut = doubleHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = testLength;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddManyNewStringElems_WhenExistInHashTable() {
        // given
        int testLength = 100;
        String toPut = RandomStringUtils.randomAlphanumeric(10);

        // when
        int nOfElemsBeforePut = stringHash.getNElem();
        for (int i = 0; i < testLength; i++) {
            stringHash.put(toPut);
        }
        int nOfElemsAfterPut = stringHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 1;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddManyNewDoubleElems_WhenExistInHashTable() {
        // given
        int testLength = 100;
        double toPut = 1.0;

        // when
        int nOfElemsBeforePut = doubleHash.getNElem();
        for (int i = 0; i < testLength; i++) {
            doubleHash.put(toPut);
        }
        int nOfElemsAfterPut = doubleHash.getNElem();

        // then
        int expectedBeforePut = 0;
        int expectedAfterPut = 1;
        assertEquals(expectedBeforePut, nOfElemsBeforePut);
        assertEquals(expectedAfterPut, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyGetDoubleElems_WhenExistInHashTable() {
        // given
        double toAdd = 1.687;
        double toGet = toAdd;

        // when
        doubleHash.put(toAdd);
        double actualValue = doubleHash.get(toGet);

        // then
        double expectedValue = toGet;
        assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void should_CorrectlyGetStringElems_WhenExistInHashTable() {
        // given
        String toAdd = "toAdd";
        String toGet = toAdd;

        // when
        stringHash.put(toAdd);
        String actualValue = stringHash.get(toGet);

        // then
        String expectedValue = toGet;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void should_CorrectlyGetManyDoubleElems_WhenExistInHashTable() {
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
            doubleHash.put(randomDouble);
        }

        // then
        for (int i = 0; i < testLength; i++) {
            double expectedValue = doubleList.get(i);
            double actualValue = doubleHash.get(doubleList.get(i));
            assertEquals(expectedValue, actualValue, 0);
        }
    }

    @Test
    public void should_CorrectlyGetManyStringElems_WhenExistInHashTable() {
        // given
        int testLength = 100;
        List<String> stringList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            String randomString = RandomStringUtils.randomAlphanumeric(10);
            while (stringList.contains(randomString)) {
                randomString = RandomStringUtils.randomAlphanumeric(10);
            }

            stringList.add(randomString);
            stringHash.put(randomString);
        }

        // then
        for (int i = 0; i < testLength; i++) {
            String expectedValue = stringList.get(i);
            String actualValue = stringHash.get(stringList.get(i));
            assertEquals(expectedValue, actualValue);
        }
    }

    @Test
    public void should_ReturnNull_WhenDeletedElementNotExistInHastTable() {
        // given
        int testLength = 100;
        double toDelete = random.nextDouble();
        List<Double> doubleList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }

            doubleList.add(randomDouble);
            doubleHash.put(randomDouble);
        }

        double beforeDelete = doubleHash.getNElem();
        while (doubleList.contains(toDelete)) {
            toDelete = random.nextDouble();
        }

        doubleHash.delete(toDelete);
        double afterDelete = doubleHash.getNElem();

        // then
        double expectedBefore = testLength;
        double expectedAfter = testLength;
        assertEquals(expectedBefore, beforeDelete, 0);
        assertEquals(expectedAfter, afterDelete, 0);
    }

    @Test
    public void should_CorrectlyDeleteOneDoubleElems_WhenExistInHastTable() {
        // given
        int testLength = 100;
        int indexToDelete = random.nextInt(testLength);
        double toDelete = 0;
        List<Double> doubleList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }

            if (i == indexToDelete) {
                toDelete = randomDouble;
            }

            doubleList.add(randomDouble);
            doubleHash.put(randomDouble);
        }

        int beforeDelete = doubleHash.getNElem();
        doubleHash.delete(toDelete);
        int afterDelete = doubleHash.getNElem();

        // then
        int expectedBefore = testLength;
        int expectedAfter = testLength - 1;
        assertEquals(expectedBefore, beforeDelete);
        assertEquals(expectedAfter, afterDelete);
    }

    @Test
    public void should_CorrectlyDeleteOneStringElems_WhenExistInHastTable() {
        // given
        int testLength = 100;
        int indexToDelete = random.nextInt(testLength);
        String toDelete = "";
        List<String> stringList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            String randomString = RandomStringUtils.randomAlphanumeric(10);
            while (stringList.contains(randomString)) {
                randomString = RandomStringUtils.randomAlphanumeric(10);
            }

            if (i == indexToDelete) {
                toDelete = randomString;
            }

            stringList.add(randomString);
            stringHash.put(randomString);
        }

        int beforeDelete = stringHash.getNElem();
        stringHash.delete(toDelete);
        int afterDelete = stringHash.getNElem();

        // then
        int expectedBefore = testLength;
        int expectedAfter = testLength - 1;
        assertEquals(expectedBefore, beforeDelete);
        assertEquals(expectedAfter, afterDelete);
    }

    @Test
    public void should_CorrectlyDeleteAllDoubleElems() {
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
            doubleHash.put(randomDouble);
        }

        for (int i = 0; i < testLength; i++) {
            doubleHash.delete(doubleList.get(i));
        }
        double actualNOfElems = doubleHash.getNElem();

        // then
        double expectedNOfElems = 0;
        assertEquals(expectedNOfElems, actualNOfElems, 0);
    }

    @Test
    public void should_CorrectlyDeleteAllStringElems() {
        // given
        int testLength = 100;
        List<String> stringList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            String randomString = RandomStringUtils.randomAlphanumeric(10);
            while (stringList.contains(randomString)) {
                randomString = RandomStringUtils.randomAlphanumeric(10);
            }

            stringList.add(randomString);
            stringHash.put(randomString);
        }

        for (int i = 0; i < testLength; i++) {
            stringHash.delete(stringList.get(i));
        }
        double actualNOfElems = stringHash.getNElem();

        // then
        double expectedNOfElems = 0;
        assertEquals(expectedNOfElems, actualNOfElems, 0);
    }

    @Test
    public void doubleElementShould_NotExistInHashTable_WhenIsDeleted() {
        // given
        int testLength = 100;
        int indexToDelete = random.nextInt(testLength);
        double toDelete = 0;
        List<Double> doubleList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            double randomDouble = random.nextDouble();
            while (doubleList.contains(randomDouble)) {
                randomDouble = random.nextDouble();
            }

            doubleList.add(randomDouble);
            doubleHash.put(randomDouble);

            if (i == indexToDelete) {
                toDelete = randomDouble;
            }
        }
        double actualdValueBeforeDelete = doubleHash.get(toDelete);
        doubleHash.delete(toDelete);
        Object actualdValueAfterDelete = doubleHash.get(toDelete);

        // then
        double expectedValueBeforeDelete = toDelete;
        Object expectedValueAfterDelete = null;
        assertEquals(expectedValueBeforeDelete, actualdValueBeforeDelete, 0);
        assertEquals(expectedValueAfterDelete, actualdValueAfterDelete);
    }

    @Test
    public void stringElementShould_NotExistInHashTable_WhenIsDeleted() {
        // given
        int testLength = 100;
        int indexToDelete = random.nextInt(testLength);
        String toDelete = "";
        List<String> stringList = new ArrayList<>();

        // when
        for (int i = 0; i < testLength; i++) {
            String randomString = RandomStringUtils.randomAlphanumeric(10);
            while (stringList.contains(randomString)) {
                randomString = RandomStringUtils.randomAlphanumeric(10);
            }

            stringList.add(randomString);
            stringHash.put(randomString);

            if (i == indexToDelete) {
                toDelete = randomString;
            }
        }
        String actualdValueBeforeDelete = stringHash.get(toDelete);
        stringHash.delete(toDelete);
        Object actualdValueAfterDelete = stringHash.get(toDelete);

        // then
        String expectedValueBeforeDelete = toDelete;
        Object expectedValueAfterDelete = null;
        assertEquals(expectedValueBeforeDelete, actualdValueBeforeDelete);
        assertEquals(expectedValueAfterDelete, actualdValueAfterDelete);
    }

}
