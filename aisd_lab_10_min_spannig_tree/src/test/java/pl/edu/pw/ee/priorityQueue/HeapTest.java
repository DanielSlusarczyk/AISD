package pl.edu.pw.ee.priorityQueue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Node;

public class HeapTest {
    private Heap<Edge> heap;

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
        Edge toInsert = null;

        // when
        heap.put(toInsert);

        // then
        assert false;
    }

    @Test
    public void should_beAbleToStoreEdges_inProperOrder() {
        // given
        int testLength = 100;
        Random random = new Random(1410);

        // when
        for (int i = 0; i < testLength; i++) {
            Node firstNode = new Node(String.valueOf(i));
            Node secondNode = new Node(String.valueOf(i + 1));
            int weight = random.nextInt(testLength);
            Edge edge = new Edge(firstNode, secondNode, weight);
            heap.put(edge);
        }

        // then
        if (heap.getSize() < 2) {
            assert false;
        }
        Edge currentEdge = heap.pop();
        while (!heap.isEmpty()) {
            Edge nextEdge = heap.pop();
            if (currentEdge.compareTo(nextEdge) < 0) {
                assert false;
            }
            currentEdge = nextEdge;
        }
        assert true;
    }
}
