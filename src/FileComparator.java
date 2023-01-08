import java.io.IOException;
import java.util.Comparator;

public class FileComparator implements Comparator<MyFile> {
    @Override
    public int compare(MyFile o1, MyFile o2) {
            MyFile file1 = (MyFile)o1;
            MyFile file2 = (MyFile)o2;
            String text1 = file1.getFileData();
            String text2 = file2.getFileData();
            String searchString1 = MyFile.getSearchString(file2.getFilePathFromRootFolder());
            String searchString2 = MyFile.getSearchString(file1.getFilePathFromRootFolder());
            int index1 = text1.indexOf(searchString1);
            int index2 = text2.indexOf(searchString2);
            if (index1 == -1 && index2 == -1) {
                return 0;
            }
            if (index1 != -1 && index2 != -1) {
                throw new RuntimeException("Can't compare two files:\n" + file1 + '\n' + file2 + '\n');
            }
            if (index2 != -1) {
                return -1;
            }
            return 1;
    }
}
