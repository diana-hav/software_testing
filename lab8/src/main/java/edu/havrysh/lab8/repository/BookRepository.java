package edu.havrysh.lab8.repository;
import edu.havrysh.lab8.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}