package pl.edu.pw.ee;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class NodeTest {
    private Node testNode;

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenSignIsNull_counterContructor(){
        Character testChar = null;
        int frequency = 1;
        testNode = new Node(testChar, frequency);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenFrequencyIsLowerThanOne_counterContructor(){
        Character testChar = 'a';
        int frequency = 0;
        testNode = new Node(testChar, frequency);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenLeftNodeIsNull_containerContructor(){
        Node leftNode = null;
        Node rightNode = new Node('a', 10);
        int frequency = 1;
        testNode = new Node(frequency, leftNode ,rightNode );
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenRightNodeIsThanOne_containerContructor(){
        Node leftNode = new Node('b', 20);
        Node rightNode = null;
        int frequency = 1;
        testNode = new Node(frequency, leftNode ,rightNode );
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenFrequencyIsLowerThanOne_containerContructor(){
        Node leftNode = new Node('b', 20);
        Node rightNode = new Node('a', 10);
        int frequency = 0;
        testNode = new Node(frequency, leftNode ,rightNode );
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenSignIsNull_mapContructor(){
        String code = "";
        Character testChar = null;
        testNode = new Node(testChar, code);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenCodeIsNull_mapContructor(){
        String code = null;
        Character testChar = 'a';
        testNode = new Node(testChar, code);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throwException_whenCodeIsEmpty_mapContructor(){
        String code = "";
        Character testChar = ' ';
        testNode = new Node(testChar, code);
    }

    @Test
    public void should_beLeaf_afterCounterContructor(){
        Character testChar = 'a';
        int frequency = 1;
        testNode = new Node(testChar, frequency);
        assertTrue(testNode.isLeaf());
    }

    @Test
    public void shouldNot_beLeaf_afterContainerContructor(){
        Node leftNode = new Node('b', 1);
        Node rightNode = new Node('a', 1);
        int frequency = 1;
        testNode = new Node(frequency, leftNode ,rightNode);
        assertFalse(testNode.isLeaf());
    }

    @Test
    public void should_beEqueal_leafsWithEqualCharacter(){
        Node firstNode = new Node('a', 1);
        Node secondNode = new Node('a', 2);
        assertTrue(firstNode.equals(secondNode));
    }

    @Test
    public void should_beEqueal_cointainerWithEqualFrequnecy(){
        Node firstNode = new Node(1, new Node('a', 1), new Node('b', 1));
        Node secondNode = new Node(1, new Node('c', 1), new Node('d', 1));
        assertTrue(firstNode.equals(secondNode));
    }
}
