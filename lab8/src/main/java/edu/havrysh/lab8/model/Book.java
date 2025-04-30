package edu.havrysh.lab8.model;

import jakarta.persistence.Embedded;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
@AllArgsConstructor
public class Book {

    @Id
    private String id;

    private String title;
    private String author;
    private String isbn;
    private String description;

    // Аудитні поля
    @Embedded
    private AuditMetadata audit;

    // Конструктор для ініціалізації об'єкта Book
    public Book() {
        this.audit = new AuditMetadata(); // Ініціалізація audit, щоб воно не було null
    }

    // Геттери і сеттери для полів (створюються через @Data або можна додавати вручну)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditMetadata getAudit() {
        return audit;
    }

    public void setAudit(AuditMetadata audit) {
        this.audit = audit;
    }

    // Перекриття методу toString для зручності виведення
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", audit=" + audit +
                '}';
    }
}

