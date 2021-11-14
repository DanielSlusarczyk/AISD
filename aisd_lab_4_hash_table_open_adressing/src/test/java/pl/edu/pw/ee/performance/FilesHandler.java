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
    private String logFile = "src/test/java/pl/edu/pw/ee/performance/results/timeLog.txt";
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

    public void writeResult(List<Long> resultsOfAdd, List<Long> resultOfGet, boolean overallTest, int size, String message) throws IOException {

        Comparator<Long> comparator = new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            }
        };
        resultsOfAdd.sort(comparator);
        resultOfGet.sort(comparator);

        if (overallTest) {
            writeOverallResult(resultsOfAdd, resultOfGet, size, message);
        } else {
            writeResult(resultsOfAdd, resultOfGet, message);
        }
        writer.close();
    }

    private void writeResult(List<Long> resultsOfAdd, List<Long> resultsOfGet, String message) throws IOException {
        writer.write(message + "\n");
        writer.write("NUMER PRZEBIEGU POMIARU CZASU DODAWANIA - CZAS [ms] \n");
        int counter = 1;
        double average = 0;

        for (Long result : resultsOfAdd) {
            writer.write(counter + " - " + result + "\n");
            average += result;

            if (counter % (resultsOfAdd.size()/3) == 0) {
                average = average / (resultsOfAdd.size()/3);
                writer.write("Average Time: " + average);
                writer.write("\n");
                average = 0;
            }
            counter++;
        }

        writer.write("NUMER PRZEBIEGU POMIARU CZARU SZUKANIA - CZAS [ms] \n");
        counter = 1;
        average = 0;
        for (Long result : resultsOfGet) {
            writer.write(counter + " - " + result + "\n");
            average += result;

            if (counter % (resultsOfAdd.size()/3) == 0) {
                average = average / (resultsOfAdd.size()/3);
                writer.write("Average Time: " + average);
                writer.write("\n");
                average = 0;
            }
            counter++;
        }
    }

    private void writeOverallResult(List<Long> resultsOfAdd, List<Long> resultsOfGet, int size, String message) throws IOException {
        writer = new BufferedWriter(new FileWriter(logFile, true));
        int counter = 1;
        double average = 0;
        writer.write(message + " [Rozmiar: " + size + "]\n");
        for (Long result : resultsOfAdd) {
            average += result;
            if (counter % (resultsOfAdd.size()/3) == 0) {
                average = average / (resultsOfAdd.size()/3);

                if (counter % ((resultsOfAdd.size()/3)*2) == 0) {
                    writer.write("Czas dodawania[ms]: " + String.valueOf(average).replace(".", ",") + "\n");
                }

                average = 0;
            }
            counter++;
        }

        average = 0;
        counter = 1;

        for (Long result : resultsOfGet) {
            average += result;
            if (counter % (resultsOfAdd.size()/3) == 0) {
                average = average / (resultsOfAdd.size()/3);

                if (counter % 20 == 0) {
                    writer.write("Czas szukania[ms]: " + String.valueOf(average).replace(".", ",") + "\n\n");
                }

                average = 0;
            }
            counter++;
        }
    }
}
