package factory;

import book.Book;

import java.io.IOException;

public interface Writer {
    String write(Book book);
    void cleanData() throws IOException;

}
