package edu.havrysh.lab8.service;
import edu.havrysh.lab8.model.AuditMetadata;
import edu.havrysh.lab8.model.Book;
import edu.havrysh.lab8.repository.BookRepository;
import edu.havrysh.lab8.request.BookCreateRequest;
import edu.havrysh.lab8.request.BookUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Book create(BookCreateRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());

        if (book.getAudit() == null) {
            book.setAudit(new AuditMetadata()); // Забезпечуємо ініціалізацію, якщо вона null
        }
        repository.save(book);

        return book;
    }


    public Book update(BookUpdateRequest request) {
        Book book = getById(request.getId());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        return repository.save(book);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
