package pl.edu.pw.ee.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.HashDoubleHashing;
import pl.edu.pw.ee.HashLinearProbing;
import pl.edu.pw.ee.HashQuadraticProbing;
import pl.edu.pw.ee.services.HashTable;

public class PerformanceTest {
    private int size = 65536;
    private List<String> wordList;

    // Time Log
    private FilesHandler filesHandler;
    private List<Long> resultsOfAdd;
    private List<Long> resultsOfGet;
    private boolean allTest = false;
    private HashTable<String> hashOpen;
    private double aRatio = 1;
    private double bRatio = 1;

    @Before
    public void setUp() throws IOException {
        filesHandler = new FilesHandler();
        resultsOfAdd = new ArrayList<>();
        resultsOfGet = new ArrayList<>();
        wordList = filesHandler.addFromFile();
    }

    @Test
    public void performanceTest_HashLinearProbing() throws IOException {
        // given
        int nmbOfRuns = 30;

        // when
        for (int runCounter = 0; runCounter < nmbOfRuns; runCounter++) {
            hashOpen = new HashLinearProbing<>(size);

            makeTest();
        }
        filesHandler.writeResult(resultsOfAdd, resultsOfGet, allTest, size, "Linear Probing");
    }

    @Test
    public void complexPerformanceTest_HashLinearProbing() throws IOException {
        double power = 0;
        double basis = 2;
        double startSize = 512;
        int nmbOfTest = 12;
        allTest = true;

        for (int i = 0; i < nmbOfTest; i++) {
            size = (int) Math.round(startSize * Math.pow(basis, power));

            performanceTest_HashLinearProbing();
            resultsOfAdd.clear();
            resultsOfGet.clear();
            power++;
        }
    }

    @Test
    public void performanceTest_HashQuadraticProbing() throws IOException {
        // given
        int nmbOfRuns = 30;

        // when
        for (int runCounter = 0; runCounter < nmbOfRuns; runCounter++) {
            hashOpen = new HashQuadraticProbing<>(size, aRatio, bRatio);

            makeTest();
        }
        String title = "Quadratic Probing [a: " + aRatio + " b: " + bRatio + "]";
        filesHandler.writeResult(resultsOfAdd, resultsOfGet, allTest, size, title);
    }

    @Test
    public void complexPerformanceTest_HashQuadraticProbing() throws IOException {
        double power = 0;
        double basis = 2;
        double startSize = 512;
        int nmbOfTest = 10;
        this.aRatio = 1;
        this.bRatio = 2;
        allTest = true;
        for (int i = 0; i < nmbOfTest; i++) {
            size = (int) Math.round(startSize * Math.pow(basis, power));

            performanceTest_HashQuadraticProbing();
            resultsOfAdd.clear();
            resultsOfGet.clear();
            power++;
        }
    }

    @Test
    public void performanceTest_HashDoubleAdressing() throws IOException {
        // given
        int nmbOfRuns = 30;

        // when
        for (int runCounter = 0; runCounter < nmbOfRuns; runCounter++) {
            hashOpen = new HashDoubleHashing<>(size);

            makeTest();
        }
        filesHandler.writeResult(resultsOfAdd, resultsOfGet, allTest, size, "Double Hashing");
    }

    @Test
    public void complexPerformanceTest_HashDoubleHashing() throws IOException {
        double power = 0;
        double basis = 2;
        double startSize = 512;
        int nmbOfTest = 12;
        allTest = true;

        for (int i = 0; i < nmbOfTest; i++) {
            size = (int) Math.round(startSize * Math.pow(basis, power));

            performanceTest_HashDoubleAdressing();
            resultsOfAdd.clear();
            resultsOfGet.clear();
            power++;
        }
    }

    private void makeTest() {
        long startTimeOfAdd;
        long endTimeOfAdd;
        long startTimeOfGet;
        long endTimeOfGet;

        startTimeOfAdd = System.currentTimeMillis();
        for (String toAdd : wordList) {
            hashOpen.put(toAdd);
        }
        endTimeOfAdd = System.currentTimeMillis();
        resultsOfAdd.add((endTimeOfAdd - startTimeOfAdd));

        startTimeOfGet = System.currentTimeMillis();
        for (String toGet : wordList) {
            hashOpen.get(toGet);
        }
        endTimeOfGet = System.currentTimeMillis();
        resultsOfGet.add(endTimeOfGet - startTimeOfGet);
    }

}
