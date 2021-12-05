package pl.edu.pw.ee.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.RbtMap;

public class PerformanceTest {
    private List<String> wordsList;
    private RbtMap<String, String> rbtMap;

    private FilesHandler filesHandler;
    private List<Integer> results;
    private List<Integer> sizes;

    @Before
    public void setUp() throws IOException {
        filesHandler = new FilesHandler();
        results = new ArrayList<>();
        sizes = new ArrayList<>();
        wordsList = filesHandler.addFromFile();
        rbtMap = new RbtMap<>();
    }

    @Test
    public void makeTestAZ() throws IOException {
        int numberOfPut = 0;
        for (String word : wordsList) {
            for (int i = 1; i <= 10; i++) {
                String key = word + i;
                String value = word + i;
                rbtMap.setValue(key, value);
                numberOfPut++;
            }

            if (numberOfPut % 1000 == 0) {
                results.add(rbtMap.getNumberOfCalls());
                sizes.add(numberOfPut);
            }
        }

        filesHandler.writeResult(results, sizes);
    }

    @Test
    public void makeTestZA() throws IOException {
        int numberOfPut = 0;
        for (int i = 0; i < wordsList.size(); i++) {
            String word = wordsList.get(wordsList.size() - i - 1);
            for (int j = 1; j <= 10; j++) {
                String key = word + j;
                String value = word + j;
                rbtMap.setValue(key, value);
                numberOfPut++;
            }

            if (numberOfPut % 1000 == 0) {
                results.add(rbtMap.getNumberOfCalls());
                sizes.add(numberOfPut);
            }
        }

        filesHandler.writeResult(results, sizes);
    }

    @Test
    public void makeTestRandom() throws IOException {
        long SEED = 1410;
        Random random = new Random(SEED);
        List<Integer> availableIndex = new ArrayList<>();
        int numberOfPut = 0;

        for (int i = 0; i < 10 * wordsList.size(); i++) {
            availableIndex.add(i);
        }

        for (int i = 0; i < 10 * wordsList.size(); i++) {
            int randomNumber = random.nextInt(availableIndex.size());
            int randomIndex = availableIndex.get(randomNumber);
            availableIndex.remove(randomNumber);

            String word = wordsList.get((randomIndex - randomIndex % 10) / 10);
            String key = word + randomIndex % 10;
            String value = word + randomIndex % 10;
            rbtMap.setValue(key, value);

            numberOfPut++;

            if (numberOfPut % 1000 == 0) {
                results.add(rbtMap.getNumberOfCalls());
                sizes.add(numberOfPut);
            }
        }

        filesHandler.writeResult(results, sizes);
    }
}
