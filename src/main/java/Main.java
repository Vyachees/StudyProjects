import book.Book;
import factory.Reader;
import factory.Writer;
import factory.filesystem.FileSystemConnectionFactory;



public class Main {
    public static void main(String[] args){

        Book newBook = new Book.Builder("MyTestBook1","Gus Gusev")
                .setPages(200)
                .setCreatedDate("2022-03-03")
                .build();

        FileSystemConnectionFactory fileSystemConnectionFactory = new FileSystemConnectionFactory();
        Writer writer=  fileSystemConnectionFactory.getWriter();

        writer.write(newBook);

        writer.write(newBook);

        Reader reader = fileSystemConnectionFactory.getReader();

        reader.read();

    }
}
