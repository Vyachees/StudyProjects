package ru.x5.school.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.x5.school.jpa.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByLastName(String lastName);
}
