package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.priorityQueue.PriorityQueue;

public class PriorityQueueTest {

    private PriorityQueue<Double> queue;

    @Before
    public void setUp() {
        queue = new PriorityQueue<>();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_throwException_whengetMaxFromEmptyHeap() {
        // given

        // when
        queue.getMax();

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_wheninsertNull() {
        // given
        Double toinsert = null;

        // when
        queue.insert(toinsert);

        // then
        assert false;
    }

    @Test
    public void should_beEmpty_atFirst() {
        // given

        // when

        // then
        assertTrue(queue.isEmpty());
    }

    @Test
    public void should_beNotEmpty_afterinsert() {
        // given

        // when
        queue.insert(1.0);

        // then
        assertFalse(queue.isEmpty());
    }

    @Test
    public void should_beEmpty_afterinsertAndgetMax() {
        // given

        // when
        queue.insert(1.0);
        queue.getMax();

        // then
        assertTrue(queue.isEmpty());
    }

    @Test
    public void should_hasSizeOfZero_atFirst() {
        // given

        // when
        int actualSize = queue.getSize();

        // then
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfOne_afterinsert() {
        // given

        // when
        queue.insert(1.0);
        int actualSize = queue.getSize();

        // then
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfTwo_afterDoubleinsert() {
        // given

        // when
        queue.insert(1.0);
        queue.insert(1.1);
        int actualSize = queue.getSize();

        // then
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_hasSizeOfZero_afterinsertAndgetMax() {
        // given

        // when
        queue.insert(1.0);
        queue.getMax();
        int actualSize = queue.getSize();

        // then
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_beAbleToStore_edges() {
        // given
        PriorityQueue<Edge> queueOfEdges = new PriorityQueue<>();
        Node[] nodes = { new Node("A"), new Node("B"), new Node("C"), new Node("D"), new Node("E") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[3], 4),
                new Edge(nodes[4], nodes[2], 2),
                new Edge(nodes[2], nodes[4], 3) };

        // when
        for (Edge edge : edges) {
            queueOfEdges.insert(edge);
        }
        int actualSize = queueOfEdges.getSize();

        // then
        int expectedSize = 4;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void should_beAbleToStore_edges_inProperOrder() {
        // given
        PriorityQueue<Edge> queueOfEdges = new PriorityQueue<>();
        Node[] nodes = { new Node("A"), new Node("B"), new Node("C"), new Node("D"), new Node("E") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[3], 4),
                new Edge(nodes[4], nodes[2], 2),
                new Edge(nodes[2], nodes[4], 3) };

        // when
        Edge[] actualEdges = new Edge[edges.length];
        for (Edge edge : edges) {
            queueOfEdges.insert(edge);
        }
        for (int index = 0; index < edges.length; index++) {
            actualEdges[index] = queueOfEdges.getMax();
        }

        // then
        Edge[] expectedEdges = { edges[0], edges[2], edges[3], edges[1] };
        assertArrayEquals(expectedEdges, actualEdges);
    }

    @Test
    public void should_beAbleToStore_equalsEdges() {
        // given
        PriorityQueue<Edge> queueOfEdges = new PriorityQueue<>();
        Node[] nodes = { new Node("A"), new Node("B") };
        Edge[] edges = {
                new Edge(nodes[0], nodes[1], 1),
                new Edge(nodes[1], nodes[0], 1),
                new Edge(nodes[1], nodes[0], 1),
                new Edge(nodes[0], nodes[1], 1) };

        // when
        for (Edge edge : edges) {
            queueOfEdges.insert(edge);
        }
        int actualSize = queueOfEdges.getSize();

        // then
        int expectedSize = 4;
        assertEquals(expectedSize, actualSize);
    }
}
