package edu.havrysh.lab8.service;

import edu.havrysh.lab8.model.Book;
import edu.havrysh.lab8.repository.BookRepository;
import edu.havrysh.lab8.request.BookCreateRequest;
import edu.havrysh.lab8.request.BookUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void whenCreateBook_thenItIsPersistedWithAuditFields() {
        BookCreateRequest request = new BookCreateRequest("1984", "George Orwell", "9780451524935", "Dystopian novel");
        Book book = service.create(request);

        assertNotNull(book.getId());
        assertEquals("1984", book.getTitle());
        assertNotNull(book.getAudit().getCreatedDate());
        assertEquals("Admin", book.getAudit().getCreatedBy());
    }

    @Test
    void whenUpdateBook_thenFieldsAndAuditAreUpdated() {
        Book book = service.create(new BookCreateRequest("Title", "Author", "123", "Desc"));
        BookUpdateRequest updateRequest = new BookUpdateRequest(book.getId(), "New Title", "New Author", "321", "New Desc");

        Book updated = service.update(updateRequest);

        assertEquals("New Title", updated.getTitle());
        assertNotNull(updated.getAudit().getLastModifiedDate());
    }

    @Test
    void whenGetAll_thenReturnsAllSavedBooks() {
        service.create(new BookCreateRequest("Book1", "A", "1", "D1"));
        service.create(new BookCreateRequest("Book2", "B", "2", "D2"));

        List<Book> books = service.getAll();

        assertEquals(2, books.size());
    }

    @Test
    void whenGetById_thenReturnsCorrectBook() {
        Book created = service.create(new BookCreateRequest("Unique", "Someone", "XYZ", "Note"));

        Book found = service.getById(created.getId());

        assertEquals(created.getId(), found.getId());
    }

    @Test
    void whenDeleteBook_thenItIsRemovedFromDB() {
        Book created = service.create(new BookCreateRequest("ToDelete", "X", "0", "Remove me"));

        service.delete(created.getId());

        assertTrue(repository.findById(created.getId()).isEmpty());
    }

    @Test
    void whenCreateMultipleBooks_thenEachHasUniqueId() {
        Book b1 = service.create(new BookCreateRequest("B1", "A1", "111", "One"));
        Book b2 = service.create(new BookCreateRequest("B2", "A2", "222", "Two"));

        assertNotEquals(b1.getId(), b2.getId());
    }

    @Test
    void whenCreateBookWithEmptyTitle_thenTitleIsEmpty() {
        Book book = service.create(new BookCreateRequest("", "Author", "123", "No title"));

        assertEquals("", book.getTitle());
    }

    @Test
    void whenUpdateNonexistentId_thenThrowsException() {
        BookUpdateRequest request = new BookUpdateRequest("nonexistent", "T", "A", "I", "D");

        assertThrows(RuntimeException.class, () -> service.update(request));
    }

    @Test
    void whenGetNonexistentId_thenThrowsException() {
        assertThrows(RuntimeException.class, () -> service.getById("nonexistent"));
    }

    @Test
    void whenCreateBook_thenAuditFieldsAreNotNull() {
        Book book = service.create(new BookCreateRequest("Audit", "Audit", "AUD123", "AuditCheck"));

        assertNotNull(book.getAudit());
        assertNotNull(book.getAudit().getCreatedDate());
    }

    @Test
    void whenBookUpdated_thenModifiedDateIsSet() {
        Book created = service.create(new BookCreateRequest("Update", "Up", "U123", "U"));
        Book updated = service.update(new BookUpdateRequest(created.getId(), "U2", "U2", "U321", "Updated"));

        assertNotNull(updated.getAudit().getLastModifiedDate());
    }

    @Test
    void whenCreateBook_thenISBNIsSavedCorrectly() {
        Book book = service.create(new BookCreateRequest("Title", "Author", "ISBN123456", "Desc"));

        assertEquals("ISBN123456", book.getIsbn());
    }

    @Test
    void whenCreateBookWithSameISBN_thenBothExist() {
        service.create(new BookCreateRequest("B1", "A", "ISBNX", "D"));
        service.create(new BookCreateRequest("B2", "A", "ISBNX", "D"));

        List<Book> found = repository.findAll();
        assertEquals(2, found.size());
    }

    @Test
    void whenCreateAndDeleteBook_thenGetAllReturnsEmpty() {
        Book book = service.create(new BookCreateRequest("Temp", "A", "TMP", "Temp"));
        service.delete(book.getId());

        assertTrue(service.getAll().isEmpty());
    }

    @Test
    void whenCreateBook_thenFieldsMatchRequest() {
        BookCreateRequest req = new BookCreateRequest("ReqTitle", "ReqAuthor", "REQISBN", "ReqDesc");
        Book book = service.create(req);

        assertEquals(req.getTitle(), book.getTitle());
        assertEquals(req.getAuthor(), book.getAuthor());
        assertEquals(req.getIsbn(), book.getIsbn());
    }

    @Test
    void whenCreateBook_thenDescriptionIsSavedCorrectly() {
        Book book = service.create(new BookCreateRequest("Title", "Author", "123", "This is a test description"));

        assertTrue(book.getDescription().contains("test"));
    }

    @Test
    void whenUpdateBook_thenOldDataIsOverwritten() {
        Book book = service.create(new BookCreateRequest("Old", "Old", "OLD", "Old"));
        Book updated = service.update(new BookUpdateRequest(book.getId(), "New", "New", "NEW", "New Desc"));

        assertEquals("New", updated.getTitle());
    }

    @Test
    void whenCreateBook_thenCanBeFoundById() {
        Book book = service.create(new BookCreateRequest("FindMe", "Finder", "F123", "Look here"));

        assertNotNull(service.getById(book.getId()));
    }

    @Test
    void whenCreateManyBooks_thenAllAuditDatesPresent() {
        for (int i = 0; i < 5; i++) {
            Book book = service.create(new BookCreateRequest("B" + i, "A", "ISBN" + i, "D"));
            assertNotNull(book.getAudit());
            assertNotNull(book.getAudit().getCreatedDate());
        }
    }

    @Test
    void whenUpdateBook_thenLastModifiedDateIsSet() {
        BookCreateRequest createRequest = new BookCreateRequest("1984", "George Orwell", "9780451524935", "Dystopian novel");
        Book book = service.create(createRequest);

        LocalDateTime initialLastModifiedDate = book.getAudit().getLastModifiedDate();

        BookUpdateRequest updateRequest = new BookUpdateRequest(book.getId(), "1984", "George Orwell", "9780451524935", "Updated Dystopian novel description");
        Book updatedBook = service.update(updateRequest);

        assertNotNull(updatedBook.getAudit().getLastModifiedDate());
        assertNotEquals(initialLastModifiedDate, updatedBook.getAudit().getLastModifiedDate());
    }


}