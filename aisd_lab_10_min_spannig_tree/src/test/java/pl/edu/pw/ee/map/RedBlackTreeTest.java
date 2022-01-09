package pl.edu.pw.ee.map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    private RedBlackTree<String, String> strRBTree;
    private RedBlackTree<Double, Double> dblRBTree;

    @Before
    public void setUp() {
        dblRBTree = new RedBlackTree<>();
        strRBTree = new RedBlackTree<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPutValueIsNull() {
        // given

        // when
        strRBTree.put("key", null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPutKeyIsNull() {
        // given

        // when
        strRBTree.put(null, "value");

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGetNull() {
        // given

        // when
        strRBTree.get(null);

        // then
        assert false;
    }

    @Test
    public void should_correctlyTraverseTree_preOrder() {
        // given
        String[] addSequence = { "g", "c", "d", "e", "b", "f", "a", "i", "h" };

        // when
        for (String added : addSequence) {
            strRBTree.put(added, added);
        }
        String actualPreOrder = strRBTree.getPreOrder();

        // then
        String expectedPreOrder = "d:d b:b a:a c:c h:h f:f e:e g:g i:i";
        assertEquals(expectedPreOrder, actualPreOrder);
    }

    @Test
    public void shouldBeLeftLeaning_afterPutElement() {
        // given
        double[] addSequence = { 1.0, 2.0 };

        // when
        for (double added : addSequence) {
            dblRBTree.put(added, added);
        }
        String actualPreOrder = dblRBTree.getPreOrder();

        // then
        String expectedPreOrder = "2.0:2.0 1.0:1.0";
        assertEquals(expectedPreOrder, actualPreOrder);
    }

    @Test
    public void shouldBeLeftLeaning_afterPutManyElement() {
        // given
        double[] addSequence = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0 };

        // when
        for (double added : addSequence) {
            dblRBTree.put(added, added);
        }
        String actualPreOrder = dblRBTree.getPreOrder();

        // then
        String expectedPreOrder = "8.0:8.0 4.0:4.0 2.0:2.0 1.0:1.0 3.0:3.0 6.0:6.0 5.0:5.0 7.0:7.0 10.0:10.0 9.0:9.0 11.0:11.0";
        assertEquals(expectedPreOrder, actualPreOrder);
    }

    @Test
    public void should_correctlyMakeLeftSwap() {
        // given
        double[] addSequence = { 1.0, 2.0 };

        // when
        dblRBTree.put(addSequence[0], addSequence[0]);
        String actualBeforeLeftSwap = dblRBTree.getPreOrder();
        dblRBTree.put(addSequence[1], addSequence[1]);
        String actualAfterLeftSwap = dblRBTree.getPreOrder();

        // then
        String expectedBeforeLeftSwap = "1.0:1.0";
        String expectedAfterLeftSwap = "2.0:2.0 1.0:1.0";
        assertEquals(expectedBeforeLeftSwap, actualBeforeLeftSwap);
        assertEquals(expectedAfterLeftSwap, actualAfterLeftSwap);
    }

    @Test
    public void should_correctlyMakeRightSwap() {
        // given
        double[] addSequence = { 3.0, 2.0, 1.0 };

        // when
        dblRBTree.put(addSequence[0], addSequence[0]);
        dblRBTree.put(addSequence[1], addSequence[1]);
        String actualBeforeRightSwap = dblRBTree.getPreOrder();
        dblRBTree.put(addSequence[2], addSequence[2]);
        String actualAfterRightSwap = dblRBTree.getPreOrder();

        // then
        String expectedBeforeRightSwap = "3.0:3.0 2.0:2.0";
        String expectedAfterRightSwap = "2.0:2.0 1.0:1.0 3.0:3.0";
        assertEquals(expectedBeforeRightSwap, actualBeforeRightSwap);
        assertEquals(expectedAfterRightSwap, actualAfterRightSwap);
    }

    @Test
    public void should_correctlyMakeLeftAndRightSwap() {
        // given
        double[] addSequence = { 3.0, 1.0, 2.0 };

        // when
        dblRBTree.put(addSequence[0], addSequence[0]);
        dblRBTree.put(addSequence[1], addSequence[1]);
        String actualBeforeSwap = dblRBTree.getPreOrder();
        dblRBTree.put(addSequence[2], addSequence[2]);
        String actualAfterSwap = dblRBTree.getPreOrder();

        // then
        String expectedBeforeSwap = "3.0:3.0 1.0:1.0";
        String expectedAfterSwap = "2.0:2.0 1.0:1.0 3.0:3.0";
        assertEquals(expectedBeforeSwap, actualBeforeSwap);
        assertEquals(expectedAfterSwap, actualAfterSwap);
    }

    @Test
    public void should_correctlyMakeRightAndLeftSwap() {
        // given
        double[] addSequence = { 3.0, 1.0, 2.0 };

        // when
        dblRBTree.put(addSequence[0], addSequence[0]);
        dblRBTree.put(addSequence[1], addSequence[1]);
        String actualAfterLeftBeforeRightSwap = dblRBTree.getPreOrder();
        dblRBTree.put(addSequence[2], addSequence[2]);
        String actualAfterLeftAfterRightSwap = dblRBTree.getPreOrder();

        // then
        String expectedAfterLeftBeforeRightSwap = "3.0:3.0 1.0:1.0";
        String expectedAfterLeftAfterRightSwap = "2.0:2.0 1.0:1.0 3.0:3.0";
        assertEquals(expectedAfterLeftBeforeRightSwap, actualAfterLeftBeforeRightSwap);
        assertEquals(expectedAfterLeftAfterRightSwap, actualAfterLeftAfterRightSwap);
    }

    @Test
    public void should_correctlyOrganizeTree_afterPut() {
        // given
        double[] addSequence = { 8.0, 18.0, 5.0, 15.0, 17.0, 25.0, 30.0, 80.0 };

        // when
        for (double added : addSequence) {
            dblRBTree.put(added, added);
        }
        String actualSwap = dblRBTree.getPreOrder();

        // then
        String expectedSwap = "17.0:17.0 8.0:8.0 5.0:5.0 15.0:15.0 25.0:25.0 18.0:18.0 80.0:80.0 30.0:30.0";
        assertEquals(expectedSwap, actualSwap);
    }
}