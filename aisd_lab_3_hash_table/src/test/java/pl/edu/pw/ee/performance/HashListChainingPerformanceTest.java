package pl.edu.pw.ee.performance;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.HashListChaining;

public class HashListChainingPerformanceTest {
    private HashListChaining<String> hashListChaining;
    private int size = 4096 * 8;
    private List<String> wordList;
    //Time Log
    private String fileName = "src/test/java/pl/edu/pw/ee/performance/wordlist.txt";
    private String logFile = "src/test/java/pl/edu/pw/ee/performance/timeLog.txt";
    private BufferedWriter writer;
    private List <Long> results;

    @Before
    public void setUp() throws IOException{
        hashListChaining = new HashListChaining<>(size);
        writer = new BufferedWriter(new FileWriter(logFile));
        results = new ArrayList<>();
        wordList = FileReader.addFromFile(fileName);

        for(String toAdd : wordList){
            hashListChaining.add(toAdd);
        }
    }

    @Test
    public <T> void performanceTest() throws IOException{
        //given
        long startTime;
        long endTime;
        //when 
        writer.write("NUMER PRZEBIEGU -> CZAS \n");
        for(int run = 0; run < 30; run++){
            startTime = System.currentTimeMillis();
            for(String toGet : wordList){
                String actual = hashListChaining.get(toGet);
                String expected = toGet;
                assertEquals(expected, actual);
            }
            endTime = System.currentTimeMillis();
            results.add(endTime - startTime);
        }
        results.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            } 
        });

        int counter = 1;
        for(Long result : results){
            writer.write(counter + " -> " + result + "\n");
            if(counter%10==0){
                writer.write("\n");
            }
            counter ++;
        }
        writer.close();
    }

}
