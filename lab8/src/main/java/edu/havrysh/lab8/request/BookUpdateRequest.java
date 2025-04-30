package edu.havrysh.lab8.request;

import lombok.Data;

@Data
public class BookUpdateRequest {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String description;

    // Додавання конструктора з усіма полями
    public BookUpdateRequest(String id, String title, String author, String isbn, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }
}
