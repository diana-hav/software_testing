package edu.havrysh.lab5.repository;

import edu.havrysh.lab5.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}

