package ch.lucaleo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public FileHandler() {
    }

    public String createFile(String filename) {
        File file = new File(filename);
        try {
            file.createNewFile();
            System.out.println("File created: " + file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file.getName();
    }

    public void writeToFile(String fileName, String content){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
