package factory.filesystem;

import book.Book;
import factory.ConnectionFactoryImpl;
import factory.Reader;
import factory.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static factory.ConnectionType.File;

class FileSystemTest {
    Book book1;
    Book book2;

    ConnectionFactoryImpl connectionFactory = new ConnectionFactoryImpl();
    Writer writer = connectionFactory.getWriter(File);

    Reader reader = connectionFactory.getReader(File);


    @BeforeEach
    void init() throws IOException {
        writer.cleanData();
        book1 = new Book.Builder("Book1","Author1").setCreatedDate("2001-01-01").build();
        book2 = new Book.Builder("Book2","Author2").setCreatedDate("2002-02-02").build();
    }

    @Test
    void writePostive() {
        String result= writer.write(book1);
        Assertions.assertEquals("Book1;Author1;2001-01-01;100"+System.getProperty( "line.separator" ),result);
    }

    @Test
    void writeNull() {
        Book wrRes =null;
        String result = writer.write(wrRes);
        Assertions.assertEquals("Object is null, can't write!",result);
    }


    @Test
    void readPositive(){


        String writeResult = writer.write(book1);
        String readResult= reader.read();
        Assertions.assertEquals(writeResult,readResult);
        String writeResult2= writer.write(book2);
        String readResult2 =reader.read();
        Assertions.assertEquals(writeResult+writeResult2,readResult2);
    }

    @Test
    void readEmpty(){
        String readResult= reader.read();
        Assertions.assertEquals("",readResult);
    }


}
