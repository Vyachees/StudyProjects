import book.Book;
import factory.ConnectionFactoryImpl;
import factory.Reader;
import factory.Writer;


import static factory.ConnectionType.File;


public class Main {
    public static void main(String[] args){

        Book newBook = new Book.Builder("MyTestBook1","Gus Gusev")
                .setPages(200)
                .setCreatedDate("2022-03-03")
                .build();

        ConnectionFactoryImpl connectionFactory = new ConnectionFactoryImpl();
        Writer writer= connectionFactory.getWriter(File);

        writer.write(newBook);

        writer.write(newBook);

        Reader reader = connectionFactory.getReader(File);

        reader.read();

    }
}
