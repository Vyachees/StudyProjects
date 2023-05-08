package factory.filesystem;

import book.Book;
import factory.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemWriter implements Writer {


    File myObj = new File("fileBooks.txt");

    @Override
    public String write(Book book) {
        if(book==null){
            return "Object is null, can't write!";
        }
        String str = book.toString();

        try(FileWriter myWriter = new FileWriter(myObj,true))
        {
            myWriter.write(str);
            myWriter.write(System.getProperty( "line.separator" ));
            myWriter.close();
            return str+System.getProperty( "line.separator" );
        } catch (IOException e) {
            return "An error occurred. "+e;
        }

    }

    public void cleanData() throws IOException {
        try(FileWriter ignored = new FileWriter(myObj,false)){
        }
    }


}
