package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class KruskalAlgorithmTest {
    private KruskalAlgorithm kruskalAlgorithm;

    @Before
    public void setUp(){
        kruskalAlgorithm = new KruskalAlgorithm();
    }



    @Test
    public void should_correctlyCreateMST_fromEachNodes() {
        // given
        String path = "src\\test\\java\\pl\\edu\\pw\\ee\\testInput\\mediumGraph.txt";
        List<Edge> listOfActualEdges;

        //when
        String actualEdges = kruskalAlgorithm.findMST(path);
        System.out.println(actualEdges);
        listOfActualEdges = initializeList(actualEdges);

        //then
        String expectedEdges = "A_32_B|B_13_C|C_17_D|B_10_G|G_7_H|H_6_E|F_2_E|E_83_J";
        List<Edge> listOfExpectedEdges = initializeList(expectedEdges);
        assert checkCorrectness(listOfExpectedEdges, listOfActualEdges);

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
