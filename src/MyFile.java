import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
public class MyFile implements Comparator{
    private final String filePath;
    public MyFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileData() {
        StringBuilder text = new StringBuilder("");
        try(FileReader reader = new FileReader(filePath))
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

    private static String getSearchString(String filePath) {
        return "require ‘" + filePath + "’";
    }

    @Override
    public int compare(Object o1, Object o2) {
        MyFile file1 = (MyFile)o1;
        MyFile file2 = (MyFile)o2;
        String text1 = file1.getFileData();
        String text2 = file2.getFileData();
        String searchString1 = getSearchString(file2.getFilePath());
        String searchString2 = getSearchString(file1.getFilePath());
        int index1 = text1.indexOf(searchString1);
        int index2 = text2.indexOf(searchString2);
        if (index1 == -1 && index2 == -1) {
            return 0;
        }
        if (index1 != -1 && index2 != -1) {
            throw new IOException("Can't compare two files:\n" + file1.getFilePath() + '\n' + file2.getFilePath() + '\n');
        }
        if (index1 != -1) {
            return -1;
        }
        if (index2 != -1) {
            return 1;
        }
        return 0;
    }
}
