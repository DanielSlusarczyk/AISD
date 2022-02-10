package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class EdgeTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenStartNodeIsNull() {
        // given
        Node start = null;
        Node end = new Node("A");
        int cost = 10;

        // when
        new Edge(start, end, cost);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenEndNodeIsNull() {
        // given
        Node start = new Node("A");
        Node end = null;
        int cost = 10;

        // when
        new Edge(start, end, cost);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenCostIsNegative() {
        // given
        Node start = new Node("A");
        Node end = new Node("B");
        int cost = -1;

        // when
        new Edge(start, end, cost);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenCompareToNull() {
        // given
        Edge firstEdge = new Edge(new Node("B"), new Node("A"), 10);
        Edge secondEdge = null;

        // when
        firstEdge.compareTo(secondEdge);

        // then
        assert false;
    }

    @Test
    public void should_beEqual_twoSameEdges() {
        // given
        Edge firstEdge = new Edge(new Node("B"), new Node("A"), 10);
        Edge secondEdge = firstEdge;

        // when
        int actualResult = firstEdge.compareTo(secondEdge);

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_compareProperly_twoEqualEdges() {
        // given
        Edge firstEdge = new Edge(new Node("B"), new Node("A"), 10);
        Edge secondEdge = new Edge(new Node("A"), new Node("B"), 10);

        // when
        int actualResult = firstEdge.compareTo(secondEdge);

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_compareProperly_differentCosts() {
        // given
        Edge firstEdge = new Edge(new Node("A"), new Node("B"), 2);
        Edge secondEdge = new Edge(new Node("A"), new Node("B"), 1);

        // when
        int actualResult = firstEdge.compareTo(secondEdge);

        // then
        int expectedResult = -1;
        assertEquals(expectedResult, actualResult);
    }
}
