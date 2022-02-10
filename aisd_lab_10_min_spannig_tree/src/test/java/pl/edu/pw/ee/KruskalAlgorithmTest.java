package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class KruskalAlgorithmTest {
    private KruskalAlgorithm kruskalAlgorithm;

    @Before
    public void setUp() {
        kruskalAlgorithm = new KruskalAlgorithm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsNull() {
        // given
        String path = null;

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsIncorrect() {
        // given
        String path = "incorrect\\path";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathLeadToDirectory() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasOnlyNewLineSign() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\newLineSign.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_breakInTheText() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\breakInTheText.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_incompleteCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incompleteCostOfEdge.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_negativeCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\negativeCostOfEdge.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_whiteSignInNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\whiteSignInNodeLabel.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_incorrectOrder() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incorrectOrder.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_numberAsNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\numberAsNodeLabel.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_spaceBeforeLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceBeforeLine.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_spaceAfterLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceAfterLine.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_deficientLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\deficientLine.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGraphIsDisconnected() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\disconnectedGraph.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGraphHasLoop() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\loop.txt";

        // when
        kruskalAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test
    public void should_correctlyCreateMST_whenFileIsEmpty() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\empty.txt";

        // when
        String actualResult = kruskalAlgorithm.findMST(path);

        // then
        String expectedResult = "";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyCreateMST_whenGraphContainsOneEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";

        // when
        String actualResult = kruskalAlgorithm.findMST(path);

        // then
        String expectedResult = "Y_10_U";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyCreateMST_1() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph.txt";
        List<Edge> listOfActualEdges;

        // when
        String actualEdges = kruskalAlgorithm.findMST(path);
        listOfActualEdges = initializeList(actualEdges);

        // then
        String expectedEdges = "A_32_B|B_13_C|C_17_D|B_10_G|G_7_H|H_6_E|F_2_E|E_83_J";
        List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
        assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
    }

    @Test
    public void should_correctlyCreateMST_2() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph_2.txt";
        List<Edge> listOfActualEdges;

        // when
        String actualEdges = kruskalAlgorithm.findMST(path);
        listOfActualEdges = initializeList(actualEdges);

        // then
        String expectedEdges = "A_5_B|A_3_G|D_8_G|F_2_E|E_4_C|C_3_H|E_1_G";
        List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
        assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
    }

    @Test
    public void should_correctlyCreateMST_equalWeights() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\equalWeightsGraph.txt";
        List<Edge> listOfActualEdges;

        // when
        String actualEdges = kruskalAlgorithm.findMST(path);
        listOfActualEdges = initializeList(actualEdges);

        // then
        String expectedEdges = "A_1_B|A_1_C|A_1_D|A_1_E|A_1_F";
        List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
        assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
    }

    @Test
    public void should_correctlyCreateMST_doubleRun() {
        // given
        String pathOne = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph_2.txt";
        String pathTwo = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\equalWeightsGraph.txt";
        List<Edge> listOfActualEdges;

        // when
        String actualEdges;
        actualEdges = kruskalAlgorithm.findMST(pathOne);
        actualEdges = kruskalAlgorithm.findMST(pathTwo);
        listOfActualEdges = initializeList(actualEdges);

        // then
        String expectedEdges = "A_1_B|A_1_C|A_1_D|A_1_E|A_1_F";
        List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
        assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
    }

    private boolean checkCorrectness(List<Edge> listOfCorrectItem, List<Edge> checkList) {
        if (listOfCorrectItem.size() != checkList.size()) {
            return false;
        }
        for (Edge correctEdge : listOfCorrectItem) {
            boolean found = false;
            for (Edge edge : checkList) {
                if (edge.compareTo(correctEdge) == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private List<Edge> initializeList(String edges) {
        List<Edge> list = new ArrayList<>();
        String[] actualEdge = edges.split("\\|");
        for (String edge : actualEdge) {
            String[] parameters = edge.split("_");
            Node start = new Node(parameters[0]);
            Node end = new Node(parameters[2]);
            int cost = Integer.parseInt(parameters[1]);

            list.add(new Edge(start, end, cost));
        }
        return list;
    }
}
