package edu.havrysh.lab8.controller;

import edu.havrysh.lab8.model.Book;
import edu.havrysh.lab8.request.BookCreateRequest;
import edu.havrysh.lab8.request.BookUpdateRequest;
import edu.havrysh.lab8.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService service;

    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Book create(@RequestBody BookCreateRequest request) {
        return service.create(request);
    }

    @PutMapping
    public Book update(@RequestBody BookUpdateRequest request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
