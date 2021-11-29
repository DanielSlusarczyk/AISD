package pl.edu.pw.ee.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.RbtMap;

public class PerformanceTest {
    private List<String> wordsList;
    private RbtMap<String, String> rbtMap;

    // Time Log
    private FilesHandler filesHandler;
    private List<Integer> results;


    @Before
    public void setUp() throws IOException {
        filesHandler = new FilesHandler();
        results = new ArrayList<>();
        wordsList = filesHandler.addFromFile();
        rbtMap = new RbtMap<>();
    }

    @Test
    public void makeTest() throws IOException{
        //Adding
        for(String word : wordsList){
            for(int i = 1; i <= 10; i++){
                rbtMap.setValue(word + i, word + i);
                results.add(rbtMap.getNumberOfCalls());
            }
        }

        //Writing
        filesHandler.writeResult(results);
    }
}

   