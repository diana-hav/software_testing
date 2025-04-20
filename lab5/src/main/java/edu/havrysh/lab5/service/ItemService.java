package edu.havrysh.lab5.service;

import edu.havrysh.lab5.model.Item;
import edu.havrysh.lab5.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Optional<Item> getById(Long id) {
        return repository.findById(id);
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

