package ru.x5.school.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.x5.school.jpa.model.Author;
import ru.x5.school.jpa.repository.AuthorRepository;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void createAuthors() {
        var author1 = new Author("authorName1", "authorLastName1");
        var author2 = new Author("authorName2", "authorLastName2");
        var author3 = new Author("authorName3", "authorLastName3");

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
    }

    @Transactional
    public Author findAuthorByLastName(String lastName) {
        return authorRepository.findAuthorByLastName(lastName);
    }

    @Transactional
    public void printAuthorAndBooks(String lastName) {
        Author author = findAuthorByLastName(lastName);
        log.info("============================printAuthorAndBooks start===========================");
        log.info("Author is {}", author);
        author.getBooks().forEach(b ->
                log.info("Book is {}", b)
        );

        log.info("============================printAuthorAndBooks end===========================");
    }

    @Transactional
    public void printBooks(String lastName) {
        Author author = findAuthorByLastName(lastName);
        log.info("============================printBooks start===========================");
        author.getBooks().forEach(b ->
                log.info("Book is {}", b)
        );
        log.info("============================printBooks end===========================");
    }
}
