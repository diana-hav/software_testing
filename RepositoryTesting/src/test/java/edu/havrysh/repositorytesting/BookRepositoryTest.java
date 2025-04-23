package edu.havrysh.repositorytesting;

import edu.havrysh.repositorytesting.model.Book;
import edu.havrysh.repositorytesting.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.saveAll(List.of(
                new Book(null, "Clean Code", "CC101", "###test-clean"),
                new Book(null, "Effective Java", "EJ303", "###test-effective"),
                new Book(null, "Domain-Driven Design", "DDD404", "###test-ddd")
        ));
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll(
                bookRepository.findAll().stream()
                        .filter(book -> book.getDescription().contains("###test"))
                        .toList()
        );
    }

    @Test
    void shouldSaveAndFindBookByName() {
        Book book = new Book(null, "The Mythical Man-Month", "MM505", "###test-mm");
        bookRepository.save(book);
        Optional<Book> found = bookRepository.findAll().stream()
                .filter(b -> b.getName().equals("The Mythical Man-Month"))
                .findFirst();
        assertTrue(found.isPresent());
    }

    @Test
    void shouldUpdateBookDescription() {
        Book book = bookRepository.findAll().get(0);
        book.setDescription("###test-updated");
        bookRepository.save(book);

        Book updated = bookRepository.findById(book.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("###test-updated", updated.getDescription());
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book(null, "To Delete", "TD606", "###test-del");
        book = bookRepository.save(book);
        bookRepository.deleteById(book.getId());

        assertFalse(bookRepository.findById(book.getId()).isPresent());
    }

    @Test
    void shouldFindBookByCode() {
        Book found = bookRepository.findAll().stream()
                .filter(book -> book.getCode().equals("EJ303"))
                .findFirst().orElse(null);
        assertNotNull(found);
    }

    @Test
    void shouldReturnAllBooksContainingTestTag() {
        List<Book> books = bookRepository.findAll().stream()
                .filter(book -> book.getDescription().contains("###test"))
                .toList();
        assertEquals(3, books.size());
    }

    @Test
    void shouldOverwriteDocumentWithSameId() {
        Book book1 = new Book("1", "Book A", "BA001", "###test-dup");
        Book book2 = new Book("1", "Book B", "BB002", "###test-dup");

        bookRepository.save(book1);
        bookRepository.save(book2); // перезаписує попередній

        Book found = bookRepository.findById("1").orElse(null);

        assertNotNull(found);
        assertEquals("Book B", found.getName());
        assertEquals("BB002", found.getCode());
    }


    @Test
    void savedBookShouldHaveIdAssigned() {
        Book book = new Book(null, "Assigned ID Book", "ID007", "###test-id");
        Book saved = bookRepository.save(book);
        assertNotNull(saved.getId());
        assertEquals(24, saved.getId().length());
    }

    @Test
    void shouldFindBookByExactName() {
        Book book = bookRepository.findAll().stream()
                .filter(b -> b.getName().equals("Effective Java"))
                .findFirst().orElse(null);
        assertNotNull(book);
    }

    @Test
    void shouldSupportBulkSave() {
        List<Book> newBooks = List.of(
                new Book(null, "Book1", "B001", "###test-bulk"),
                new Book(null, "Book2", "B002", "###test-bulk")
        );
        bookRepository.saveAll(newBooks);
        long count = bookRepository.findAll().stream()
                .filter(b -> b.getDescription().equals("###test-bulk"))
                .count();
        assertEquals(2, count);
    }

    @Test
    void shouldReturnEmptyWhenNoBookMatches() {
        Book book = bookRepository.findAll().stream()
                .filter(b -> b.getCode().equals("INVALID_CODE"))
                .findFirst().orElse(null);
        assertNull(book);
    }
}
