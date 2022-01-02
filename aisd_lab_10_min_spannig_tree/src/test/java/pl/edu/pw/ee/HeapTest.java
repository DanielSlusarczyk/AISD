package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.priorityQueue.Heap;

public class HeapTest {

    private Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_throwException_whenPopFromEmptyHeap() {
        // given

        // when
        heap.pop();

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPutNull() {
        // given
        Double toPut = null;

        // when
        heap.put(toPut);

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

        // when
        heap.put(1.0);

        // then
        assertFalse(heap.isEmpty());
    }

    @Test
    public void should_beEmpty_afterPutAndPop() {
        // given

        // when
        heap.put(1.0);
        heap.pop();

        // then
        assertTrue(heap.isEmpty());
    }

    @Test
    public void should_hasSizeOfZero_atFirst() {
        // given

        // when
        int actualSize = heap.getSize();

        // then
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfOne_afterPut() {
        // given

        // when
        heap.put(1.0);
        int actualSize = heap.getSize();

        // then
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfTwo_afterDoublePut() {
        // given

        // when
        heap.put(1.0);
        heap.put(1.1);
        int actualSize = heap.getSize();

        // then
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfZero_afterPutAndPop() {
        // given

        // when
        heap.put(1.0);
        heap.pop();
        int actualSize = heap.getSize();

        // then
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_beAbleToStore_edges() {
        // given
        Heap<Edge> heapOfEdges = new Heap<>();
        Node[] nodes = { new Node("A"), new Node("B"), new Node("C"), new Node("D"), new Node("E") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[3], 4),
                new Edge(nodes[4], nodes[2], 2),
                new Edge(nodes[2], nodes[4], 3) };

        // when
        for (Edge edge : edges) {
            heapOfEdges.put(edge);
        }
        int actualSize = heapOfEdges.getSize();

        // then
        int expectedSize = 4;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_beAbleToStore_edges_inProperOrder() {
        // given
        Heap<Edge> heapOfEdges = new Heap<>();
        Node[] nodes = { new Node("A"), new Node("B"), new Node("C"), new Node("D"), new Node("E") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[3], 4),
                new Edge(nodes[4], nodes[2], 2),
                new Edge(nodes[2], nodes[4], 3) };

        // when
        Edge[] actualEdges = new Edge[edges.length];
        for (Edge edge : edges) {
            heapOfEdges.put(edge);
        }
        for (int index = 0; index < edges.length; index++) {
            actualEdges[index] = heapOfEdges.pop();
        }

        // then
        Edge[] expectedEdges = { edges[0], edges[2], edges[3], edges[1] };
        assertArrayEquals(expectedEdges, actualEdges);
    }

    @Test
    public void should_beAbleToStore_equalsEdges() {
        // given
        Heap<Edge> heapOfEdges = new Heap<>();
        Node[] nodes = { new Node("A"), new Node("B") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[0], 1),
                new Edge(nodes[1], nodes[0], 1),
                new Edge(nodes[0], nodes[1], 1) };

        // when
        for (Edge edge : edges) {
            heapOfEdges.put(edge);
        }
        int actualSize = heapOfEdges.getSize();

        // then
        int expectedSize = 4;
        assertEquals(expectedSize, actualSize);
    }
}
