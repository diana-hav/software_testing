package edu.havrysh.repositorytesting.repository;

/**
 * @author diana
 * @project RepositoryTesting
 * @class BookRepository
 * @version 1.0.0
 * @since 2025.04.23
 */

import edu.havrysh.repositorytesting.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
