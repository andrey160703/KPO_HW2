import java.util.Comparator;
public class MyFile implements Comparator{
    private final String filePath;
    public MyFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public int compare(Object o1, Object o2) {
        String file1 = ((MyFile)o1).getFilePath();
        String file2 = ((MyFile)o2).getFilePath();
        
        return 0;
    }
}
