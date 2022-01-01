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
    public void schould_throwException_whenPathIsNull() {
        // given
        String path = null;

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenPathIsIncorrect() {
        // given
        String path = "incorrect\\path";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenPathLeadToDirectory() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileIsEmpty() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\empty.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasOnlyNewLineSign() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\newLineSign.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_breakInTheText() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\breakInTheText.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_incompleteCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incompleteCostOfEdge.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_negativeCostOfEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\negativeCostOfEdge.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_whiteSignInNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\whiteSignInNodeLabel.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_incorrectOrder() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\incorrectOrder.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_numberAsNodeLabel() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\numberAsNodeLabel.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_spaceBeforeLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceBeforeLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_spaceAfterLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\spaceAfterLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenFileHasIncorrectFormat_deficientLine() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\deficientLine.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void schould_throwException_whenGraphIsDisconnected() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\disconnectedGraph.txt";

        // when
        primAlgorithm.findMST(path);

        // then
        assert false;
    }

    @Test
    public void schould_correctlyCreateMST_whenGraphContainsOneEdge() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";

        // when
        String actualResult = primAlgorithm.findMST(path);

        // then
        String expectedResult = "Y_10_U";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void schould_correctlyCreateMST_whenGraphContainsOneEdge_fromEachNode() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\oneEdgeGraph.txt";
        List<Edge> listOfActualEdges;
        String[] firstNodes = { "Y", "U"};

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
    public void schould_correctlyCreateMST_fromEachNode() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph.txt";
        List<Edge> listOfActualEdges;
        String[] firstNodes = { "A", "B", "C", "D", "E", "F", "G", "H", "J" };

        for (int index = 0; index < firstNodes.length; index++) {
            // when
            primAlgorithm.setFirstNode(firstNodes[index]);
            String actualEdges = primAlgorithm.findMST(path);
            listOfActualEdges = initializeList(actualEdges);

            // then
            String expectedEdges = "D_17_C|B_32_A|G_10_B|C_20_F|G_10_H|H_6_E|F_2_E|E_83_J";
            List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
            assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);
        }
    }

    private boolean checkCorrectness(List<Edge> listOfCorrectItem, List<Edge> checkList) {
        if (listOfCorrectItem.size() != checkList.size()) {
            return false;
        }
        for (Edge edge : listOfCorrectItem) {
            if (!checkList.contains(edge)) {
                return true;
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
