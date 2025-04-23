package edu.havrysh.repositorytesting.service;

/**
 * @author diana
 * @project RepositoryTesting
 * @class BookService
 * @version 1.0.0
 * @since 2025.04.23
 */

import edu.havrysh.repositorytesting.model.Book;
import edu.havrysh.repositorytesting.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @PostConstruct
    public void initBooks() {
        deleteAllBooks();
        saveInitialBooks();
    }

    public void saveInitialBooks() {
        bookRepository.saveAll(List.of(
                Book.builder()
                        .id("1")
                        .name("Clean Code")
                        .code("CC101")
                        .description("A Handbook of Agile Software Craftsmanship")
                        .build(),
                Book.builder()
                        .id("2")
                        .name("The Pragmatic Programmer")
                        .code("PP202")
                        .description("Your Journey to Mastery")
                        .build(),
                Book.builder()
                        .id("3")
                        .name("Effective Java")
                        .code("EJ303")
                        .description("Best practices for Java programming")
                        .build()
        ));
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }


    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(String id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setName(updatedBook.getName());
                    existingBook.setCode(updatedBook.getCode());
                    existingBook.setDescription(updatedBook.getDescription());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }
}
