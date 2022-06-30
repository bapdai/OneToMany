package com.example.onetomany.service;

import com.example.onetomany.entity.Item;
import com.example.onetomany.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Integer id){
        return itemRepository.findById(id);
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }
}
