import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {

    private void isListCorrect(ArrayList files) {
        for (int i = 0; i < files.size(); i++) {
            MyFile checkFile = (MyFile)files.get(i);
            String checkText = checkFile.getFileData();
            for (int j = i + 1; j < files.size(); j++) {
                MyFile currentFile = (MyFile)files.get(j);
                String searchString = MyFile.getSearchString(currentFile.getFilePathFromRootFolder());
                int index = checkText.indexOf(searchString);
                if (index != -1) {
                    throw new RuntimeException("There is a loop in the files");
                }
            }
        }
    }

    private void mergeTextToFile(ArrayList files) {
        StringBuilder finalText = new StringBuilder("");
        for (int i = 0; i < files.size(); i++) {
            MyFile file = (MyFile)files.get(i);
            finalText.append(file.getFileData() + '\n');
        }
        try(FileWriter writer = new FileWriter("output.txt", false))
        {
            writer.write(finalText.toString());
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void processing() {
        File currentFile = new File(".");
        Path filePath = Paths.get(currentFile.getAbsolutePath());
        String rootDir = filePath.getParent().toString() + "/initial folder";
        ArrayList files = new ArrayList();
        try {
            Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile).forEach(el -> files.add(new MyFile(el.toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current list:");
        for (Object el : files) {
            System.out.println(el);
        }
        try {
            Collections.sort(files, new FileComparator());
            isListCorrect(files);
            System.out.println("Sorted list:");
            for (Object el : files) {
                System.out.println(el);
            }
            mergeTextToFile(files);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
