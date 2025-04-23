package edu.havrysh.repositorytesting.model;

/**
 * @author diana
 * @project RepositoryTesting
 * @class Book
 * @version 1.0.0
 * @since 2025.04.23
 */

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    private String id;
    private String name;
    private String code;
    private String description;
}
