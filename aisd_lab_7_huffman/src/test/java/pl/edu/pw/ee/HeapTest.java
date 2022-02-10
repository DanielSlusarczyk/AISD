package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {

    private Heap<Node> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_throwException_whenPopByEmptyHeap() {
        // given

        // when
        heap.pop();

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPutNull() {
        // given
        Node node = null;

        // when
        heap.put(node);

        // then
        assert false;
    }

    @Test
    public void should_beEmpty_atFirst() {
        // given

        // when

        // then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void should_beNotEmpty_afterPut() {
        // given
        Node node = new Node();

        // when
        heap.put(node);
        // then
        assertFalse(heap.isEmpty());
    }

    @Test
    public void should_beEmpty_afterPutAndPop() {
        // given
        Node node = new Node();

        // when
        heap.put(node);
        heap.pop();

        // then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void should_hasSizeOfZero_atFirst() {
        // given

        // when
        int actual = heap.getSize();

        // then
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfOne_afterPut() {
        // given
        Node node = new Node();

        // when
        heap.put(node);
        int actual = heap.getSize();

        // then
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfTwo_afterDoublePut() {
        // given
        Node firstNode = new Node('a', 1);
        Node secondNode = new Node('b', 1);

        // when
        heap.put(firstNode);
        heap.put(secondNode);
        int actual = heap.getSize();

        // then
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void should_hasSizeOfZero_afterPutAndPop() {
        // given
        Node node = new Node();

        // when
        heap.put(node);
        heap.pop();
        int actual = heap.getSize();

        // then
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void should_popMinElement_positiveElements() {
        // given

        // when
        heap.put(new Node('a', 4));
        heap.put(new Node('b', 2));
        heap.put(new Node('c', 5));
        heap.put(new Node('d', 3));
        heap.put(new Node('e', 3));
        Node actual = heap.pop();

        // then
        Node expected = new Node('b', 2);
        assertEquals(expected, actual);
    }
}
