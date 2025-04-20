package edu.havrysh.lab5.controller;

import edu.havrysh.lab5.model.Item;
import edu.havrysh.lab5.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("/")
    public List<Item> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable Long id) {
        return service.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found with id: " + id));
    }

    @PostMapping("/")
    public Item create(@RequestBody Item item) {
        return service.save(item);
    }

    @PutMapping("/")
    public Item update(@RequestBody Item item) {
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

