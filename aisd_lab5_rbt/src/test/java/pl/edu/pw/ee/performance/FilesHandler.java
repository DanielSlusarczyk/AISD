package pl.edu.pw.ee.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesHandler {
    private String fileName = "src/test/java/pl/edu/pw/ee/performance/wordlist.txt";
    private String logFile = "src/test/java/pl/edu/pw/ee/performance/results/logTime.txt";
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private File file;
    private Scanner reader;

    public FilesHandler() {
        try {
            fileWriter = new FileWriter(logFile);
            writer = new BufferedWriter(fileWriter);
            file = new File(fileName);
            reader = new Scanner(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("There is no required file");
        }
    }

    public List<String> addFromFile(){
        List<String> wordsList = new ArrayList<>();

        while (reader.hasNextLine()) {
            wordsList.add(reader.nextLine());
        }

        reader.close();
        return wordsList;
    }

    public void writeResult(List<Integer> results, List<Integer> sizes) throws IOException {
        for (int i = 0; i < results.size(); i++) {
            writer.write(sizes.get(i) + ":" + results.get(i) + "\n");
        }
        writer.close();
    }
}
