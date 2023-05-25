package ru.x5.school.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.x5.school.jpa.model.Author;
import ru.x5.school.jpa.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Integer countBooksByAuthor(Author author);
}
