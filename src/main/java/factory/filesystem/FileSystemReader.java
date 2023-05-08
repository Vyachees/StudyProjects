package factory.filesystem;

import factory.Reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileSystemReader implements Reader {
    File myObj = new File("fileBooks.txt");

    @Override
    public String read() {
        try( FileReader reader = new FileReader(myObj))
        {
            StringBuilder res = new StringBuilder();
            int i;
            while((i=reader.read())!=-1){
                //System.out.print((char)i);
                res.append((char)i);}
            return String.valueOf( res);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "";
    }
}
