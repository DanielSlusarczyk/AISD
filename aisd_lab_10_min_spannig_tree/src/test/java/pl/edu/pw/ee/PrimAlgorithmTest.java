package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PrimAlgorithmTest {
    private PrimAlgorithm primAlgorithm;

    @Before
    public void setUp() {
        primAlgorithm = new PrimAlgorithm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsNull() {
        // given
        String path = null;

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathIsIncorrect() {
        // given
        String path = "incorrect\\path";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenPathLeadToDirectory() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasOnlyNewLineSign() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\newLineSign.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_breakInTheText() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\breakInTheText.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_incompleteCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incompleteCostOfEdge.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_negativeCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\negativeCostOfEdge.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_whiteSignInNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\whiteSignInNodeLabel.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_incorrectOrder() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incorrectOrder.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_numberAsNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\numberAsNodeLabel.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_spaceBeforeLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceBeforeLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_spaceAfterLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceAfterLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenFileHasIncorrectFormat_deficientLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\deficientLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGraphIsDisconnected() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\disconnectedGraph.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwException_whenGraphHasLoop() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\loop.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test
    public void should_correctlyCreateMST_whenFileIsEmpty() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\empty.txt";

        // when
        String actualResult = primAlgorithm.findMST(path);

        // then
        String expectedResult = "";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_workCorrectly_whenFirstNodeSetAsNull() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";

        // when
        primAlgorithm.setFirstNode(null);
        String actualResult = primAlgorithm.findMST(path);

        // then
        String expectedResult = "Y_10_U";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyCreateMST_whenGraphContainsOneEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";

        // when
        String actualResult = primAlgorithm.findMST(path);

        // then
        String expectedResult = "Y_10_U";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void should_correctlyCreateMST_whenGraphContainsOneEdge_fromEachNode() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";
        List<Edge> listOfActualEdges;
        String[] firstNodes = { "Y", "U" };

        for (int index = 0; index < firstNodes.length; index++) {
            // when
            primAlgorithm.setFirstNode(firstNodes[index]);
            String actualEdges = primAlgorithm.findMST(path);
            listOfActualEdges = initializeList(actualEdges);

            // then
            String expectedEdges = "Y_10_U";
            List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
            assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
        }
    }

    @Test
    public void should_correctlyCreateMST_fromEachNodes() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph.txt";
        List<Edge> listOfActualEdges;
        String[] startNodes = { "A", "B", "C", "D", "E", "F", "G", "H" };

        for (int index = 0; index < startNodes.length; index++) {
            // when
            primAlgorithm.setFirstNode(startNodes[index]);
            String actualEdges = primAlgorithm.findMST(path);
            listOfActualEdges = initializeList(actualEdges);

            // then
            String expectedEdges = "A_32_B|B_13_C|C_17_D|B_10_G|G_7_H|H_6_E|F_2_E|E_83_J";
            List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
            assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
        }
    }

    @Test
    public void should_createDifferentMST_fromEachNode_equalWeights() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\equalWeightsGraph.txt";
        List<Edge> listOfActualEdges;
        String[] firstNodes = { "A", "B", "C", "D", "E", "F" };

        for (int index = 0; index < firstNodes.length; index++) {
            // when
            primAlgorithm.setFirstNode(firstNodes[index]);
            String actualEdges = primAlgorithm.findMST(path);
            listOfActualEdges = initializeList(actualEdges);

            // then
            String[] expectedEdges = {
                    "A_1_F|F_1_E|E_1_D|D_1_C|C_1_B",
                    "B_1_C|C_1_D|D_1_E|E_1_F|F_1_A",
                    "C_1_D|D_1_E|E_1_F|F_1_B|B_1_A",
                    "D_1_E|E_1_F|F_1_C|C_1_B|B_1_A",
                    "E_1_F|F_1_D|D_1_C|C_1_B|B_1_A",
                    "F_1_E|E_1_D|D_1_C|C_1_B|B_1_A"
            };
            List<Edge> listOfExpectedEdges = initializeList(expectedEdges[index]);
            assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
        }
    }

    private boolean checkCorrectness(List<Edge> listOfCorrectItem, List<Edge> checkList) {
        if (listOfCorrectItem.size() != checkList.size()) {
            return false;
        }
        for (Edge edge : listOfCorrectItem) {
            if (!checkList.contains(edge)) {
                System.out.println("Brak: " + edge);
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
