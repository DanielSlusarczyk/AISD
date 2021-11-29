package pl.edu.pw.ee.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FilesHandler {
    private String fileName = "src/test/java/pl/edu/pw/ee/performance/wordlist.txt";
    private String logFile = "src/test/java/pl/edu/pw/ee/performance/results/logTime.txt";
    private FileWriter fileWriter;
    private BufferedWriter writer;

    public FilesHandler() throws IOException {
        fileWriter = new FileWriter(logFile);
        writer = new BufferedWriter(fileWriter);
    }

    public List<String> addFromFile() throws IOException {
        List<String> wordsList = new ArrayList<>();
        File file = new File(fileName);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            wordsList.add(reader.nextLine());
        }

        reader.close();
        return wordsList;
    }

    public void writeResult(List<Integer> results) throws IOException {
        for (int i = 1; i <= results.size(); i++) {
            writer.write(i + ":" + results.get(i - 1) + "\n");
        }
        writer.close();
    }
}
