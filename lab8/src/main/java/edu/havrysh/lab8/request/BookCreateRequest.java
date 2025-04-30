package edu.havrysh.lab8.request;
import lombok.Data;

@Data
public class BookCreateRequest {
    private String title;
    private String author;
    private String isbn;
    private String description;

    public BookCreateRequest(String title, String author, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }
}
