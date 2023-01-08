import java.io.FileReader;
import java.io.IOException;

public class MyFile {
    private final String FILEPATH;
    private final String FILE_DATA;
    private final String ROOT_FOLDER = "initial folder";

    public MyFile(String filePath) {
        this.FILEPATH = filePath;
        this.FILE_DATA = readFile();
    }

    public String getFilePathFromRootFolder() {
        int index = FILEPATH.indexOf(ROOT_FOLDER);
        return FILEPATH.substring(index + ROOT_FOLDER.length() + 1);
    }

    public String getFilePath() {
        return FILEPATH;
    }

    public String getFileData() {
        return FILE_DATA;
    }

    private String readFile() {
        StringBuilder text = new StringBuilder("");
        try(FileReader reader = new FileReader(FILEPATH))
        {
            int c;
            while((c=reader.read())!=-1){
                text.append((char)c);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return text.toString();
    }

    public static String getSearchString(String filePath) {
        return "require ‘" + filePath + "’";
    }

    @Override
    public String toString() {
        return getFilePathFromRootFolder();
    }
}
