package ru.x5.school.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.x5.school.jpa.service.AuthorService;
import ru.x5.school.jpa.service.BookService;
import ru.x5.school.jpa.service.GenreService;

@SpringBootApplication
public class SpringBootJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(SpringBootJpaApplication.class, args);

        BookService bookService = context.getBean(BookService.class);
        AuthorService authorService = context.getBean(AuthorService.class);
        GenreService genreService = context.getBean(GenreService.class);

        authorService.createAuthors();
        bookService.createBooks();
        genreService.createGenres();

       //Вывести авторов, у которых больше 3 книг
        bookService.getAuthorsWithThreeMoreBooks();

        //Найти книги по фамилии автора
        authorService.printBooks("authorLastName1");

        //Найти книги с двумя указанными жанрами
        genreService.getBooksByTwoGenres("genre1","genre2");
    }

}
