package com.example.onetomany.restapi;

import com.example.onetomany.entity.Cart;
import com.example.onetomany.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
    // CURD
    @Autowired
    CartService cartService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> getList(){
        return ResponseEntity.ok(cartService.findAll());
    }
    //
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Cart> optionalCart = cartService.findById(id);
        if (!optionalCart.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCart.get());
    }
    //
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.save(cart));
    }

    //    Sua thong tin(U)
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Cart> update(@PathVariable Integer id, @RequestBody Cart cart) {
        Optional<Cart> optionalCart = cartService.findById(id);
        if (!optionalCart.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Cart exitsCart = optionalCart.get();
        //     map object
        exitsCart.setName(cart.getName());
        exitsCart.setListItem(cart.getListItem());
        return ResponseEntity.ok(cartService.save(exitsCart));
    }
    //        Xoa thong tin
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!cartService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        cartService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
