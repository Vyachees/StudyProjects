package ru.x5.school.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.x5.school.jpa.model.Book;
import ru.x5.school.jpa.model.Genre;
import ru.x5.school.jpa.repository.BookRepository;
import ru.x5.school.jpa.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreService.class);
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void createGenres() {

        List<Book> bookList1 = new ArrayList<>();
        List<Book> bookList2 = new ArrayList<>();
        List<Book> bookList3 = new ArrayList<>();

        bookList1.add(bookRepository.findAll().get(0));
        bookList1.add(bookRepository.findAll().get(1));

        bookList2.add(bookRepository.findAll().get(2));
        bookList2.add(bookRepository.findAll().get(3));

        bookList3.add(bookRepository.findAll().get(4));
        bookList3.add(bookRepository.findAll().get(5));

        Genre genre1 = new Genre("genre1", bookList1);
        Genre genre2 = new Genre("genre2", bookList2);
        Genre genre3 = new Genre("genre3", bookList3);

        genreRepository.save(genre1);
        genreRepository.save(genre2);
        genreRepository.save(genre3);
    }

    //Найти книги с двумя указанными жанрами
    @Transactional
    public List<Book> getBooksByTwoGenres(String genreName1, String genreName2) {
        List<Book> bookListResult = new ArrayList<>();

        Genre genre1 = genreRepository.findGenreByName(genreName1);
        Genre genre2 = genreRepository.findGenreByName(genreName2);

        bookListResult.addAll(genre1.getBooks());
        bookListResult.addAll(genre2.getBooks());

        log.info("=================getBooksByTwoGenres start==================================");
        log.info(bookListResult.toString());
        log.info("=================getBooksByTwoGenres end==================================");

        return bookListResult;
    }
}
