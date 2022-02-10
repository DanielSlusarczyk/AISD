package pl.edu.pw.ee.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.HashListChaining;

public class HashListChainingPerformanceTest {
    private int size = 4096 * 1;
    private HashListChaining<String> hashListChaining;
    private List<String> wordList;

    // Time Log
    private FilesHandler filesHandler;
    private List<Long> results;
    private boolean allTest = false;

    @Before
    public void setUp() throws IOException {
        hashListChaining = new HashListChaining<>(size);
        results = new ArrayList<>();
        filesHandler = new FilesHandler();
        wordList = filesHandler.addFromFile();

        for (String toAdd : wordList) {
            hashListChaining.add(toAdd);
        }
    }

    @Test
    public void performanceTest() throws IOException {
        // given
        long startTime;
        long endTime;
        int nmbOfRuns = 30;

        // when
        for (int runCounter = 0; runCounter < nmbOfRuns; runCounter++) {
            startTime = System.currentTimeMillis();

            for (String toGet : wordList) {
                hashListChaining.get(toGet);
            }

            endTime = System.currentTimeMillis();
            results.add((endTime - startTime));
        }
        filesHandler.writeResult(results, allTest, size);
    }

    @Test
    public void complexPerformanceTest() throws IOException {
        double power = 0;
        double basis = 2;
        double startSize = 4096;
        int nmbOfTest = 7;
        allTest = true;

        for (int i = 0; i < nmbOfTest; i++) {
            size = (int) Math.round(startSize * Math.pow(basis, power));
            hashListChaining = new HashListChaining<>(size);

            for (String toAdd : wordList) {
                hashListChaining.add(toAdd);
            }

            performanceTest();
            results.clear();
            power++;
        }
    }

}
