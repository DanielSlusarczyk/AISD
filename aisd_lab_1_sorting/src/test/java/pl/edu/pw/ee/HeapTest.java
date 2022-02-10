package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {

    private Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_ThrowArrayIndexOutOfBoundsException_popByEmptyHeap() {
        // when
        heap.pop();
    }

    @Test
    public void should_beEmpty_atFirst() {
        // then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void should_beNotEmpty_afterPut() {
        // when
        heap.put(1.0);
        // then
        assertFalse(heap.isEmpty());
    }

    @Test
    public void should_beEmpty_afterPutAndPop() {
        // when
        heap.put(1.0);
        heap.pop();
        // then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void should_hasSizeOfZero_atFirst() {
        // when
        int actual = heap.getSize();
        // then
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfOne_afterPut() {
        // when
        heap.put(1.0);
        int actual = heap.getSize();
        // then
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfTwo_afterDoublePut() {
        // when
        heap.put(1.0);
        heap.put(1.1);
        int actual = heap.getSize();
        // then
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfZero_afterPutAndPop() {
        // when
        heap.put(1.0);
        heap.pop();
        int actual = heap.getSize();
        // then
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void should_popMaxElement_positiveElements() {
        // when
        heap.put(1.0);
        heap.put(2.0);
        heap.put(3.0);
        heap.put(2.0);
        heap.put(1.0);
        double actual = heap.pop();
        // then
        double expected = 3.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void should_popMaxElement_negativeElements() {
        // when
        heap.put(-1.0);
        heap.put(-2.0);
        heap.put(-3.0);
        heap.put(-2.0);
        heap.put(-1.0);
        heap.put(0.0);
        double actual = heap.pop();
        // then
        double expected = 0.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void should_popMaxElement_mixedElements() {
        // when
        heap.put(-1.0);
        heap.put(2.0);
        heap.put(-3.0);
        heap.put(-2.0);
        heap.put(1.0);
        heap.put(0.0);
        double actual = heap.pop();
        // then
        double expected = 2.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void should_popProperOrder() {
        // when
        heap.put(-1.0);
        heap.put(2.0);
        heap.put(-3.0);
        heap.put(-2.0);
        heap.put(1.0);
        heap.put(0.0);
        double[] actual = new double[6];
        for (int i = 0; i < 6; i++) {
            actual[i] = heap.pop();
        }
        // then
        double[] expected = { 2.0, 1.0, 0.0, -1.0, -2.0, -3.0 };
        assertArrayEquals(expected, actual, 0);
    }
}
