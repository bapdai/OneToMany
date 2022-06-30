package com.example.onetomany.restapi;

import com.example.onetomany.entity.Cart;
import com.example.onetomany.entity.Item;
import com.example.onetomany.service.CartService;
import com.example.onetomany.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
public class ItemApi {
    // CURD
    @Autowired
    ItemService itemService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getList(){
        return ResponseEntity.ok(itemService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Item> optionalItem = itemService.findById(id);
        if (!optionalItem.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalItem.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Item> update(@PathVariable Integer id, @RequestBody Item item) {
        Optional<Item> optionalItem = itemService.findById(id);
        if (!optionalItem.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Item exitsItem = optionalItem.get();
        //     map object
        exitsItem.setId(item.getId());
        exitsItem.setName(item.getName());
        exitsItem.setQuantity(item.getQuantity());
        exitsItem.setPrice(item.getPrice());
        return ResponseEntity.ok(itemService.save(exitsItem));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!itemService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
