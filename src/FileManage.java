import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

public interface FileManage {


    List <String> createEmails(List<User> users) throws ParseException;
    List<String> readFromFile(Path readFilePath);

    void writeToFile(List<String> readedFile, Path writeFilePath);

    List <User> createUsers(List<String> readedFile, int camelTag);


}
