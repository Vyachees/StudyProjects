package ru.x5.school.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.x5.school.jpa.model.Book;
import ru.x5.school.jpa.model.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
    List<Genre> findGenresByBooksIn(Collection<Book> books);
}
