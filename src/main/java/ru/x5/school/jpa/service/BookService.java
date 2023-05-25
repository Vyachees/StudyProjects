package ru.x5.school.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.x5.school.jpa.model.Author;
import ru.x5.school.jpa.model.Book;
import ru.x5.school.jpa.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(AuthorService authorService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void createBooks() {
        Author author1 = authorService.findAuthorByLastName("authorLastName1");
        Author author2 = authorService.findAuthorByLastName("authorLastName2");
        Author author3 = authorService.findAuthorByLastName("authorLastName3");

        var book1 = new Book("bookName1", author1);
        var book2 = new Book("bookName2", author1);
        var book3 = new Book("bookName3", author1);
        var book4 = new Book("bookName4", author1);
        var book5 = new Book("bookName5", author2);
        var book6 = new Book("bookName6", author3);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
    }

    @Transactional
    public void findCountBooks(String lastName) {
        log.info("=============================findCountBooks start=================================");
        log.info(bookRepository.countBooksByAuthor(authorService.findAuthorByLastName(lastName)).toString());
        log.info("=============================findCountBooks end=================================");
    }

    @Transactional
    public List<Author> getAuthorsWithThreeMoreBooks() {
        List<Author> authorsResult = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            Author author = book.getAuthor();
            if (bookRepository.countBooksByAuthor(author) > 3 && !authorsResult.contains(author)) {
                authorsResult.add(author);
            }
        }
        log.info("=============================getAuthorsWithThreeMoreBooks start=================================");
        log.info(authorsResult.toString());
        log.info("=============================getAuthorsWithThreeMoreBooks end=================================");
        return authorsResult;
    }

    @Transactional
    public List<Book> getBooksByAuthorLastName() {
        List<Book> resultBooks = new ArrayList<>();
        return resultBooks;
    }


}
