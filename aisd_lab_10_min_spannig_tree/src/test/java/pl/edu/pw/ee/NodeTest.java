package pl.edu.pw.ee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class NodeTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenLabelIsNull() {
        // given
        String label = null;

        // when
        new Node(label);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenLabelIsEmpty() {
        // given
        String label = "";

        // when
        new Node(label);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenCompareToNull() {
        // given
        String label = "A";

        // when
        new Node(label).compareTo(null);

        // then
        assert false;
    }

    @Test
    public void should_storeInformation_aboutVisit() {
        // given
        String label = "A";
        Node node = new Node(label);

        // when
        boolean beforeVisit = node.isVisited();
        node.setVisited();
        boolean afterVisit = node.isVisited();

        // then
        assertFalse(beforeVisit);
        assertTrue(afterVisit);
    }

    @Test
    public void should_notBeEqual_whenNodeIsNull() {
        // given
        Node firstNode = new Node("A");
        Node secondNode = null;

        // when
        boolean actualResult = firstNode.equals(secondNode);

        // then
        boolean expectedResult = false;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_compareProperly_twoEqualsNodes() {
        // given
        Node firstNode = new Node("A");
        Node secondNode = new Node("A");

        // when
        int actualResult = firstNode.compareTo(secondNode);

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_compareProperly_twoDifferentNodes() {
        // given
        Node firstNode = new Node("A");
        Node secondNode = new Node("B");

        // when
        int actualResult = firstNode.compareTo(secondNode);

        // then
        int expectedResult = -1;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_compareProperly_differentStatusOfVisit() {
        // given
        Node firstNode = new Node("A");
        Node secondNode = new Node("A");

        // when
        firstNode.setVisited();
        int actualResult = firstNode.compareTo(secondNode);

        // then
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }
}
