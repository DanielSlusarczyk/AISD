package pl.edu.pw.ee.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FilesHandler {

    private String fileName = "src/test/java/pl/edu/pw/ee/performance/wordlist.txt";
    private String logFile = "src/test/java/pl/edu/pw/ee/performance/timeLog.txt";
    private BufferedWriter writer;
    private FileWriter fileWriter;

    public FilesHandler() throws IOException {
        fileWriter = new FileWriter(logFile);
        writer = new BufferedWriter(fileWriter);
    }

    public List<String> addFromFile() throws FileNotFoundException {
        List<String> wordsList = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                wordsList.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return wordsList;
    }

    public void writeResult(List<Long> results, boolean overallTest, int size) throws IOException {

        results.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            }
        });
        if (overallTest) {
            writeOverallResult(results, size);
        } else {
            writeComplexResult(results);
        }
        writer.close();
    }

    private void writeComplexResult(List<Long> results) throws IOException {  
        writer.write("NUMER PRZEBIEGU - CZAS [ms] \n");
        int counter = 1;
        double average = 0;

        for (Long result : results) {
            writer.write(counter + " - " + result + "\n");
            average += result;

            if (counter % 10 == 0) {
                average = average / 10;
                writer.write("Average Time: " + average);
                writer.write("\n");
                average = 0;
            }
            counter++;
        }

    }

    private void writeOverallResult(List<Long> results, int size) throws IOException {
        writer = new BufferedWriter(new FileWriter(logFile, true));
        int counter = 1;
        double average = 0;
        for (Long result : results) {
            average += result;
            if (counter % 10 == 0) {
                average = average / 10;
                if (counter % 20 == 0) {
                    writer.write(size + " : " + average);
                    writer.write("\n");
                }
                average = 0;
            }
            counter++;
        }
    }
}
