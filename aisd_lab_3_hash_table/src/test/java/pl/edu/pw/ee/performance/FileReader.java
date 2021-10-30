package pl.edu.pw.ee.performance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> addFromFile(String fileName) throws FileNotFoundException{
        List<String> list = new ArrayList<>();
        try{
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                list.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        }
        return list;
    }    
}
